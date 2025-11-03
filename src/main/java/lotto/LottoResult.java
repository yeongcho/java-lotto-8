package lotto;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class LottoResult {
    private final Map<Rank, Integer> resultMap = new EnumMap<>(Rank.class);

    public LottoResult(List<Rank> ranks) {
        for (Rank rank : Rank.values()) {
            resultMap.put(rank, 0);
        }
        ranks.forEach(rank -> resultMap.put(rank, resultMap.get(rank) + 1));
    }

    public Map<Rank, Integer> getResultMap() {
        return resultMap;
    }

    public double calculateProfitRate(int amount) {
        long totalPrize = resultMap.entrySet().stream()
                .mapToLong(entry -> (long) entry.getKey().getReward() * entry.getValue())
                .sum();
        return Math.round((totalPrize / (double) amount) * 1000) / 10.0;
    }
}
