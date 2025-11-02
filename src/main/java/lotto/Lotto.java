package lotto;

import java.util.*;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);

        // 정렬된 불변 리스트를 생성하여 this.numbers에 할당
        List<Integer> sortedNumbers = new ArrayList<>(numbers);
        Collections.sort(sortedNumbers);
        this.numbers = Collections.unmodifiableList(sortedNumbers);
    }

    private void validate(List<Integer> numbers) {
        if (numbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 6개여야 합니다.");
        }

        // 중복 검증 (LottoTest 요구 사항)
        Set<Integer> uniqueNumbers = new HashSet<>(numbers);
        if (uniqueNumbers.size() != 6) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 중복될 수 없습니다.");
        }

        // 1~45 범위 검증 (기능 요구 사항)
        for (int number : numbers) {
            validateNumberRange(number);
        }
    }
    // (범위 검증 로직 분리 - indent 2 및 메서드 15라인 준수)
    private void validateNumberRange(int number) {
        if (number < 1 || number > 45) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.");
        }
    }

    // 출력을 위해 getNumbers() 메서드 추가
    public List<Integer> getNumbers() {
        return numbers;
    }

}
