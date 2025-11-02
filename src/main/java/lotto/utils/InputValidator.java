package lotto.utils;

import lotto.service.LottoMachine;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputValidator {
    private static final String COMMA_DELIMITER = ",";

    public int validatePurchaseAmount(String input) {
        int amount = validateIsNumber(input, "[ERROR] 구입 금액은 숫자여야 합니다.");
        validateIsThousandUnit(amount);
        return amount;
    }

    private int validateIsNumber(String input, String errorMessage) {
        try {
            return Integer.parseInt(input.trim()); // 공백 제거
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(errorMessage);
        }
    }

    private void validateIsThousandUnit(int amount) {
        if (amount <= 0 || amount % LottoMachine.LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1,000원 단위여야 합니다.");
        }
    }

    public List<Integer> validateWinningNumbers(String input) {
        try {
            return Arrays.stream(input.split(COMMA_DELIMITER))
                    .map(String::trim) // 공백 제거
                    .map(numberStr -> validateIsNumber(numberStr, "[ERROR] 당첨 번호는 숫자여야 합니다."))
                    .collect(Collectors.toList());
            // Lotto 생성자가 개수, 중복, 범위 검증을 해줄 것임
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("[ERROR] 당첨 번호는 쉼표(,)로 구분된 숫자여야 합니다.");
        }
    }

    public int validateBonusNumber(String input) {
        return validateIsNumber(input, "[ERROR] 보너스 번호는 숫자여야 합니다.");
        // WinningLotto 생성자가 범위, 중복 검증을 해줄 것임
    }
}
