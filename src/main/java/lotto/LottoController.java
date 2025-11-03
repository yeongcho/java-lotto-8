package lotto;

import java.util.List;
import java.util.stream.Collectors;

public class LottoController {

    public void run() {
        int amount = retryUntilSuccess(InputValue::inputPurchaseAmount);
        List<Lotto> lottos = LottoGenerator.generateLottos(amount);
        OutputView.printLottos(lottos);

        List<Integer> winningNumbers = retryUntilSuccess(InputValue::inputWinningNumbers);
        int bonusNumber = retryUntilSuccess(() -> InputValue.inputBonusNumber(winningNumbers));

        WinningLotto winningLotto = new WinningLotto(winningNumbers, bonusNumber);
        List<Rank> ranks = lottos.stream()
                .map(winningLotto::match)
                .collect(Collectors.toList());

        LottoResult result = new LottoResult(ranks);
        OutputView.printResult(result, amount);
    }

    private <T> T retryUntilSuccess(SupplierWithException<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    @FunctionalInterface
    interface SupplierWithException<T> {
        T get();
    }
}
