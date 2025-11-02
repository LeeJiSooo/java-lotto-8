package lotto.domain;

import java.util.Arrays;

public enum Rank {
    FIRST(6, 2_000_000_000L),  // 6개 일치
    SECOND(5, 30_000_000L), // 5개 + 보너스
    THIRD(5, 1_500_000L),  // 5개 일치
    FOURTH(4, 50_000L),   // 4개 일치
    FIFTH(3, 5_000L),    // 3개 일치
    MISS(0, 0L);         // 꽝

    private final int matchCount;
    private final long prize;

    Rank(int matchCount, long prize) {
        this.matchCount = matchCount;
        this.prize = prize;
    }

    public long getPrize() {
        return prize;
    }

    public int getMatchCount() {
        return matchCount;
    }

    /**
     * 일치 개수와 보너스 여부로 등수를 결정 (else, switch 미사용)
     */
    public static Rank valueOf(int matchCount, boolean hasBonus) {
        if (matchCount == 6) {
            return FIRST;
        }
        if (matchCount == 5 && hasBonus) {
            return SECOND;
        }
        if (matchCount == 5) { // 보너스 없는 5개
            return THIRD;
        }

        // 3, 4, 0-2개 일치 (stream 사용)
        return Arrays.stream(values())
                // 5개 일치(THIRD)는 위에서 처리했으므로, 6, 5(bonus)가 아닌 것들 중 찾음
                .filter(rank -> rank != FIRST && rank != SECOND && rank.matchCount == matchCount)
                .findFirst()
                .orElse(MISS);
    }
}
