package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.EnumMap;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class LottoResultTest {
    @Test
    @DisplayName("수익률 계산 테스트 (8000원 투자, 5000원 당첨)")
    void calculateProfitRate_OneFifthPrize() {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        statistics.put(Rank.FIFTH, 1); // 5등 1개
        LottoResult result = new LottoResult(statistics);

        int purchaseAmount = 8000;
        double profitRate = result.calculateProfitRate(purchaseAmount);

        // (5000 / 8000) * 100 = 62.5
        assertThat(profitRate).isEqualTo(62.5);
    }

    @Test
    @DisplayName("수익률 계산 테스트 (1000원 투자, 0원 당첨)")
    void calculateProfitRate_NoPrize() {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        LottoResult result = new LottoResult(statistics);

        int purchaseAmount = 1000;
        double profitRate = result.calculateProfitRate(purchaseAmount);

        assertThat(profitRate).isEqualTo(0.0);
    }

    @Test
    @DisplayName("수익률 계산 시 소수점 둘째 자리에서 반올림한다")
    void calculateProfitRate_Rounding() {
        Map<Rank, Integer> statistics = new EnumMap<>(Rank.class);
        statistics.put(Rank.FIFTH, 1); // 5000원
        LottoResult result = new LottoResult(statistics);

        int purchaseAmount = 14000;
        // (5000 / 14000) * 100 = 35.714... -> 35.7
        double profitRate = result.calculateProfitRate(purchaseAmount);

        assertThat(profitRate).isEqualTo(35.7);
    }
}
