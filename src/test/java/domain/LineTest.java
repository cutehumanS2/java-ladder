package domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import util.TestLineItemGenerator;
import view.LineItem;
import java.util.stream.Stream;

class LineTest {

    private static Stream<Arguments> methodSourceEnum() {
        return Stream.of(
                Arguments.arguments(LineItem.CONNECTED, LineItem.CONNECTED, LineItem.UNCONNECTED),
                Arguments.arguments(LineItem.UNCONNECTED, LineItem.CONNECTED, LineItem.CONNECTED)
        );
    }

    @DisplayName("현재 위치에서 LineItem을 놓을 수 있는지 확인한다.")
    @ParameterizedTest
    @MethodSource("methodSourceEnum")
    void checkCanAddLineItem(LineItem actual, LineItem lineItem, LineItem expected) {
        // given
        Line line = new Line(5);
        line.getLineItems().add(actual);

        // when
        LineItem result = line.decideLineItem(1, lineItem);

        // then
        assertThat(result).isEqualTo(expected);
    }

    @DisplayName("사다리의 LineItem이 columnLength에 맞게 생성되는지 확인한다.")
    @Test
    void checkCreatedLineItemCount() {
        // given
        TestLineItemGenerator lineItemGenerator = new TestLineItemGenerator(LineItem.CONNECTED);
        int columnLength = 4;
        Line line = new Line(columnLength);

        // when
        int result = line.makeLine(lineItemGenerator).size();

        // then
        assertThat(result).isEqualTo(columnLength-1);
    }

    @DisplayName("현재 위치에서 사다리가 옆으로 연결되어 있으면 옆으로 이동한다.")
    @Test
    void moveLineIfConnected() {
        // given
        TestLineItemGenerator lineItemGenerator = new TestLineItemGenerator(LineItem.CONNECTED);
        int columnLength = 4;
        int position = 0;
        Line line = new Line(columnLength);
        line.makeLine(lineItemGenerator);

        // when
        position = line.move(position);

        // then
        assertThat(position).isEqualTo(1);
    }
}
