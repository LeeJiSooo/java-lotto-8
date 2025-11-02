package lotto.domain;

import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> statistics;

    public LottoResult(Map<Rank, Integer> statistics) {
        this.statistics = statistics;
    }

    public Map<Rank, Integer> getStatistics() {
        return statistics;
    }

    public double calculateProfitRate(int purchaseAmount) {
        long totalPrize = calculateTotalPrize();

        if (purchaseAmount == 0) {
            return 0.0;
        }

        double rate = (double) totalPrize / purchaseAmount * 100.0;
        // 소수점 둘째 자리에서 반올림 (요구 사항)
        return Math.round(rate * 10.0) / 10.0;
    }

    private long calculateTotalPrize() {
        return statistics.entrySet().stream()
                .mapToLong(entry -> entry.getKey().getPrize() * entry.getValue())
                .sum();
    }
}
