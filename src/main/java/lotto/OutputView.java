package lotto;

import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printLottos(List<Lotto> lottos) {
        System.out.println(lottos.size() + "개를 구매했습니다.");
        for (Lotto lotto : lottos) {
            System.out.println(lotto.getNumbers());
        }
    }

    public static void printResult(LottoResult result, int amount) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---");
        Map<Rank, Integer> map = result.getResultMap();

        // 요구된 출력 순서대로 출력
        printLine("3개 일치", Rank.FIFTH, map.getOrDefault(Rank.FIFTH, 0));
        printLine("4개 일치", Rank.FOURTH, map.getOrDefault(Rank.FOURTH, 0));
        printLine("5개 일치", Rank.THIRD, map.getOrDefault(Rank.THIRD, 0));
        printLine("5개 일치, 보너스 볼 일치", Rank.SECOND, map.getOrDefault(Rank.SECOND, 0));
        printLine("6개 일치", Rank.FIRST, map.getOrDefault(Rank.FIRST, 0));

        double rate = result.calculateProfitRate(amount);
        System.out.printf("총 수익률은 %.1f%%입니다.%n", rate);
    }

    private static void printLine(String label, Rank rank, int count) {
        System.out.printf("%s (%s) - %d개%n", label, formatMoney(rank.getReward()), count);
    }

    private static String formatMoney(int amount) {
        return String.format("%,d원", amount);
    }
}