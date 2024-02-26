package domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;

class LadderResultsTest {

    private static Stream<Arguments> LadderResults() {
        return Stream.of(
                Arguments.arguments(List.of("냥", "2000", "5000")),
                Arguments.arguments(List.of("꽝", "-2000", "5000"))
        );
    }

    @DisplayName("실행 결과가 꽝 또는 자연수가 아닐 경우 예외가 발생한다.")
    @ParameterizedTest
    @MethodSource("LadderResults")
    void occurExceptionIfLadderResultsIsNotLoseOrNaturalNumber(List<String> ladderResults) {
        int columnLength = 3;
        assertThatThrownBy(() -> new LadderResults(ladderResults, columnLength))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("실행 결과는 꽝 또는 자연수만 입력 가능합니다.");
    }

    @DisplayName("실행 결과 개수가 참여자 수와 다른 경우 예외가 발생한다.")
    @Test
    void occurExceptionIfLadderResultsIsInvalidLength() {
        int columnLength = 3;
        assertThatThrownBy(() -> new LadderResults(List.of("꽝", "2000", "3000", "꽝"), columnLength))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("실행 결과 개수는 참여자 수와 일치해야 합니다.");
    }
}