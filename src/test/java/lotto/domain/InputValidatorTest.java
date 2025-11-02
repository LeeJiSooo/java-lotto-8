package lotto.domain;

import lotto.utils.InputValidator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class InputValidatorTest {
    private final InputValidator validator = new InputValidator();

    @Test
    @DisplayName("구입 금액이 숫자가 아니면 예외 발생")
    void validatePurchaseAmount_NotNumber() {
        assertThatThrownBy(() -> validator.validatePurchaseAmount("1000j"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 숫자여야 합니다.");
    }

    @Test
    @DisplayName("구입 금액이 1000원 단위가 아니면 예외 발생")
    void validatePurchaseAmount_NotThousandUnit() {
        assertThatThrownBy(() -> validator.validatePurchaseAmount("1500"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
    }

    @Test
    @DisplayName("구입 금액이 0 이하면 예외 발생")
    void validatePurchaseAmount_ZeroOrLess() {
        assertThatThrownBy(() -> validator.validatePurchaseAmount("0"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호 입력이 쉼표로 구분된 숫자가 아니면 예외 발생")
    void validateWinningNumbers_NotNumbers() {
        assertThatThrownBy(() -> validator.validateWinningNumbers("1,2,3,4,5,a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 당첨 번호는 숫자여야 합니다.");
    }

    @Test
    @DisplayName("당첨 번호가 공백을 포함해도 정상 파싱")
    void validateWinningNumbers_WithSpaces() {
        List<Integer> numbers = validator.validateWinningNumbers("1, 2, 3, 4, 5, 6");
        assertThat(numbers).isEqualTo(List.of(1, 2, 3, 4, 5, 6));
    }

    @Test
    @DisplayName("보너스 번호가 숫자가 아니면 예외 발생")
    void validateBonusNumber_NotNumber() {
        assertThatThrownBy(() -> validator.validateBonusNumber("a"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR] 보너스 번호는 숫자여야 합니다.");
    }
}
