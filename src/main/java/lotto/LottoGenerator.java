package lotto;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import java.util.stream.Collectors;

public class LottoGenerator {
    public static List<Lotto> generateLottos(int amount) {
        int count = amount / 1000;
        return java.util.stream.IntStream.range(0, count)
                .mapToObj(i -> new Lotto(
                        Randoms.pickUniqueNumbersInRange(1, 45, 6)
                                .stream()
                                .sorted()
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());
    }
}
