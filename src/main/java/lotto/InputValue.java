package lotto;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class InputValue {
    public static int inputPurchaseAmount() {
        System.out.println("구입금액을 입력해 주세요.");
        String input = Console.readLine();
        int amount = Integer.parseInt(input);
        validatePurchaseAmount(amount);
        return amount;
    }

    private static void validatePurchaseAmount(int amount) {
        if (amount % 1000 != 0) {
            throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
        }
    }

    public static List<Integer> inputWinningNumbers() {
        System.out.println("당첨 번호를 입력해 주세요.");
        String input = Console.readLine();
        List<Integer> numbers = Arrays.stream(input.split(","))
                .map(String::trim)
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        LottoValidator.validateNumbers(numbers);;
        return numbers;
    }

    public static int inputBonusNumber(List<Integer> winningNumbers) {
        System.out.println("보너스 번호를 입력해 주세요.");
        int bonus = Integer.parseInt(Console.readLine());
        LottoValidator.validateBonus(bonus, winningNumbers);
        return bonus;
    }
}
