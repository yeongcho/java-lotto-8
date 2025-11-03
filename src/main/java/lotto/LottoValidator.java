package lotto;

import java.util.List;

public class LottoValidator {
    public static void validateNumbers(List<Integer> numbers) {
        if (numbers.size() != 6 || numbers.stream().distinct().count() != 6) {
            throw new IllegalArgumentException("[ERROR] 중복되지 않은 6개의 숫자여야 합니다.");
        }
        if (numbers.stream().anyMatch(n -> n < 1 || n > 45)) {
            throw new IllegalArgumentException("[ERROR] 로또 번호는 1~45 사이여야 합니다.");
        }
    }

    public static void validateBonus(int bonus, List<Integer> winningNumbers) {
        if (bonus < 1 || bonus > 45) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 1~45 사이여야 합니다.");
        }
        if (winningNumbers.contains(bonus)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 중복될 수 없습니다.");
        }
    }
}

