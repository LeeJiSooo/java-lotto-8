package lotto.controller;

import lotto.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.Rank;
import lotto.domain.WinningLotto;
import lotto.service.LottoMachine;
import lotto.utils.InputValidator;
import lotto.view.InputView;
import lotto.view.OutputView;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoGameController {
    private final InputView inputView;
    private final OutputView outputView;
    private final InputValidator validator;
    private final LottoMachine lottoMachine;

    public LottoGameController() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
        this.validator = new InputValidator();
        this.lottoMachine = new LottoMachine();
    }

    public void run() {
        // 1. 구입 금액 입력 (예외 시 재입력)
        int purchaseAmount = getPurchaseAmountWithRetry();

        // 2. 로또 발행 및 출력
        int lottoCount = lottoMachine.calculateLottoCount(purchaseAmount);
        List<Lotto> userLottos = lottoMachine.purchaseLottos(purchaseAmount);
        outputView.printPurchasedLottos(userLottos);

        // 3. 당첨 번호 및 보너스 번호 입력 (예외 시 재입력)
        WinningLotto winningLotto = getWinningLottoWithRetry();

        // 4. 결과 계산
        LottoResult result = calculateResults(userLottos, winningLotto);

        // 5. 결과 출력
        outputView.printStatistics(result.getStatistics());
        outputView.printProfitRate(result.calculateProfitRate(purchaseAmount));
    }

    // Indent 2를 지키기 위한 메서드 분리 (예외 처리)
    private int getPurchaseAmountWithRetry() {
        while (true) {
            try {
                String input = inputView.readPurchaseAmount();
                return validator.validatePurchaseAmount(input);
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private WinningLotto getWinningLottoWithRetry() {
        Lotto winningNumbers = getWinningNumbersWithRetry();
        // Indent 2를 위해 보너스 번호 입력 로직도 분리
        return getBonusNumberWithRetry(winningNumbers);
    }

    private Lotto getWinningNumbersWithRetry() {
        while (true) {
            try {
                String input = inputView.readWinningNumbers();
                List<Integer> numbers = validator.validateWinningNumbers(input);
                return new Lotto(numbers); // Lotto 생성자가 6개,중복,범위 검증
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    private WinningLotto getBonusNumberWithRetry(Lotto winningNumbers) {
        while (true) {
            try {
                String bonusInput = inputView.readBonusNumber();
                int bonusNumber = validator.validateBonusNumber(bonusInput);
                return new WinningLotto(winningNumbers, bonusNumber); // WinningLotto가 보너스 번호 검증
            } catch (IllegalArgumentException e) {
                outputView.printErrorMessage(e.getMessage());
            }
        }
    }

    // 결과 계산
    private LottoResult calculateResults(List<Lotto> userLottos, WinningLotto winningLotto) {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        // Map 초기화 (출력 시 0개도 나와야 함)
        for (Rank rank : Rank.values()) {
            if (rank != Rank.MISS) { // 꽝(MISS)은 통계에 포함X
                statistics.put(rank, 0);
            }
        }

        for (Lotto userLotto : userLottos) {
            Rank rank = winningLotto.calculateRank(userLotto);
            if (rank != Rank.MISS) {
                statistics.put(rank, statistics.get(rank) + 1);
            }
        }

        return new LottoResult(statistics);
    }
}
