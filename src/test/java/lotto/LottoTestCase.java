package lotto;

import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.assertj.core.api.Assertions.*;

public class LottoTestCase {
    @Test
    void 구입금액이_1000원_단위가_아니면_예외발생() {
        assertThatThrownBy(() -> new InputValue() {
            {
                int amount = 1500;
                if (amount % 1000 != 0) {
                    throw new IllegalArgumentException("[ERROR] 구입 금액은 1000원 단위여야 합니다.");
                }
            }
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("[ERROR]");
    }
    @Test
    void 로또번호가_6개가_아니면_예외발생() {
        List<Integer> wrongSize = List.of(1, 2, 3, 4, 5);
        assertThatThrownBy(() -> new Lotto(wrongSize))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("6개여야");
    }
    @Test
    void 로또번호에_중복이_있으면_예외발생() {
        List<Integer> duplicated = List.of(1, 1, 2, 3, 4, 5);
        assertThatThrownBy(() -> new Lotto(duplicated))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void 로또번호가_범위를_벗어나면_예외발생() {
        List<Integer> invalid = List.of(0, 2, 3, 4, 5, 6);
        assertThatThrownBy(() -> new WinningLotto(invalid, 7))
                .isInstanceOf(IllegalArgumentException.class);
    }
    @Test
    void Rank_매칭_정상작동() {
        assertThat(Rank.valueOf(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.valueOf(5, true)).isEqualTo(Rank.SECOND);
        assertThat(Rank.valueOf(5, false)).isEqualTo(Rank.THIRD);
        assertThat(Rank.valueOf(4, false)).isEqualTo(Rank.FOURTH);
        assertThat(Rank.valueOf(3, false)).isEqualTo(Rank.FIFTH);
    }
    @Test
    void 당첨결과_정상계산() {
        List<Integer> winning = List.of(1, 2, 3, 4, 5, 6);
        int bonus = 7;
        WinningLotto winningLotto = new WinningLotto(winning, bonus);

        Lotto first = new Lotto(List.of(1, 2, 3, 4, 5, 6)); // 1등
        Lotto second = new Lotto(List.of(1, 2, 3, 4, 5, 7)); // 2등

        assertThat(winningLotto.match(first)).isEqualTo(Rank.FIRST);
        assertThat(winningLotto.match(second)).isEqualTo(Rank.SECOND);
    }
    @Test
    void 수익률_반올림_정상작동() {
        List<Rank> ranks = Arrays.asList(
                Rank.FIFTH, Rank.FIFTH, Rank.NONE, Rank.NONE
        );
        LottoResult result = new LottoResult(ranks);
        double rate = result.calculateProfitRate(4000);
        assertThat(rate).isEqualTo(250.0);
    }
}