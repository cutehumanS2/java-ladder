package domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.TestLineItemGenerator;
import view.LineItem;
import java.util.List;
import java.util.stream.Stream;

class PrizesTest {

    private static Stream<Arguments> Prizes() {
        return Stream.of(
                Arguments.arguments(List.of("냥", "2000", "5000")),
                Arguments.arguments(List.of("꽝", "-2000", "5000"))
        );
    }

    @DisplayName("실행 결과가 꽝 또는 자연수가 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("Prizes")
    void occurExceptionIfLadderResultsIsNotLoseOrNaturalNumber(List<String> prizes) {
        // given
        int columnLength = 3;

        // when & then
        assertThatThrownBy(() -> new Prizes(prizes, columnLength))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Prizes.ERROR_IS_NOT_LOSE_OR_NATURAL_NUMBER);
    }

    @DisplayName("실행 결과 개수가 참여자 수와 다른 경우 예외가 발생한다.")
    @Test
    void occurExceptionIfPrizesIsInvalidLength() {
        // given
        int columnLength = 3;

        // when & then
        assertThatThrownBy(() -> new Prizes(List.of("꽝", "2000", "3000", "꽝"), columnLength))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(Prizes.ERROR_IS_INVALID_LENGTH);
    }

    @DisplayName("현재 위치의 사다리 실행 결과를 반환한다.")
    @Test
    void returnPrizeByCurrentPosition() {
        // given
        int columnLength = 4;
        int position = 0;
        TestLineItemGenerator lineItemGenerator = new TestLineItemGenerator(LineItem.CONNECTED);
        Ladder ladder = Ladder.of(new Height("5"), columnLength, lineItemGenerator);
        ladder.playLadderGame(position);
        Prizes prizes = new Prizes(List.of("꽝", "5000", "꽝", "3000"), columnLength);

        // when
        String result = prizes.findPrizeByPosition(position);

        // then
        assertThat(result).isEqualTo(prizes.getPrizes().get(2));
    }
}
