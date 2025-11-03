package lotto;

import java.util.Arrays;

public enum Rank {
    FIRST(6, false, 2_000_000_000),
    SECOND(5, true, 30_000_000),
    THIRD(5, false, 1_500_000),
    FOURTH(4, false, 50_000),
    FIFTH(3, false, 5_000),
    NONE(0, false, 0);

    private final int count;
    private final boolean bonus;
    private final int reward;

    Rank(int count, boolean bonus, int reward) {
        this.count = count;
        this.bonus = bonus;
        this.reward = reward;
    }

    public static Rank valueOf(int count, boolean bonus) {
        return Arrays.stream(values())
                .filter(rank -> rank.count == count && (!rank.bonus || bonus))
                .findFirst()
                .orElse(NONE);
    }

    public int getReward() {
        return reward;
    }
}