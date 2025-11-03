package lotto;

import java.util.List;

public class WinningLotto {
    private final List<Integer> winningNumbers;
    private final int bonusNumber;

    public WinningLotto(List<Integer> winningNumbers, int bonusNumber) {
        this.winningNumbers = winningNumbers;
        this.bonusNumber = bonusNumber;
    }

    public Rank match(Lotto lotto) {
        int matchCount = (int) lotto.getNumbers().stream()
                .filter(winningNumbers::contains)
                .count();
        boolean bonusMatch = lotto.getNumbers().contains(bonusNumber);
        return Rank.valueOf(matchCount, bonusMatch);
    }
}
