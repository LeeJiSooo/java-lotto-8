package lotto.domain;

import lotto.Lotto;

public class WinningLotto {
    private final Lotto lotto;
    private final int bonusNumber;

    public WinningLotto(Lotto lotto, int bonusNumber) {
        validate(lotto, bonusNumber);
        this.lotto = lotto;
        this.bonusNumber = bonusNumber;
    }

    private void validate(Lotto lotto, int bonusNumber) {
        // 보너스 번호 1~45 범위 검증 (요구 사항)
        if (bonusNumber < 1 || bonusNumber > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
        // 보너스 번호가 당첨 번호와 중복되는지 검증 (요구 사항)
        if (lotto.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    /**
     * 구매한 로또를 받아 당첨 등수(Rank)를 반환
     */
    public Rank calculateRank(Lotto userLotto) {
        int matchCount = userLotto.countMatchingNumbers(this.lotto);
        boolean hasBonus = userLotto.contains(this.bonusNumber);

        return Rank.valueOf(matchCount, hasBonus);
    }
}
