package lotto.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RankTest {
    @Test
    @DisplayName("6개 일치 시 FIRST 반환")
    void valueOf_6_matches() {
        assertThat(Rank.valueOf(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.valueOf(6, true)).isEqualTo(Rank.FIRST); // 보너스 여부 상관없음
    }

    @Test
    @DisplayName("5개 일치 및 보너스 일치 시 SECOND 반환")
    void valueOf_5_matches_with_bonus() {
        assertThat(Rank.valueOf(5, true)).isEqualTo(Rank.SECOND);
    }

    @Test
    @DisplayName("5개 일치 및 보너스 불일치 시 THIRD 반환")
    void valueOf_5_matches_no_bonus() {
        assertThat(Rank.valueOf(5, false)).isEqualTo(Rank.THIRD);
    }

    @Test
    @DisplayName("4개 일치 시 FOURTH 반환")
    void valueOf_4_matches() {
        assertThat(Rank.valueOf(4, true)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.valueOf(4, false)).isEqualTo(Rank.FOURTH);
    }

    @Test
    @DisplayName("3개 일치 시 FIFTH 반환")
    void valueOf_3_matches() {
        assertThat(Rank.valueOf(3, true)).isEqualTo(Rank.FIFTH);
        assertThat(Rank.valueOf(3, false)).isEqualTo(Rank.FIFTH);
    }

    @Test
    @DisplayName("2개 이하 일치 시 MISS 반환")
    void valueOf_2_or_less_matches() {
        assertThat(Rank.valueOf(2, true)).isEqualTo(Rank.MISS);
        assertThat(Rank.valueOf(1, false)).isEqualTo(Rank.MISS);
        assertThat(Rank.valueOf(0, true)).isEqualTo(Rank.MISS);
    }
}
