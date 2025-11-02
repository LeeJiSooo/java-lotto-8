package lotto.service;

import camp.nextstep.edu.missionutils.Randoms;
import lotto.Lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoMachine {
    public static final int LOTTO_PRICE = 1000;

    public List<Lotto> purchaseLottos(int purchaseAmount) {
        int count = calculateLottoCount(purchaseAmount);
        List<Lotto> lottos = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            lottos.add(generateLotto());
        }
        return lottos;
    }

    public int calculateLottoCount(int purchaseAmount) {
        return purchaseAmount / LOTTO_PRICE;
    }

    private Lotto generateLotto() {
        // 1~45 사이 6개 중복 없는 숫자 뽑기
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(1, 45, 6);
        return new Lotto(numbers); // Lotto 생성자가 정렬까지 처리
    }
}
