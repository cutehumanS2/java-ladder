package view;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import java.util.List;

public class InputValidatorTest {

    private static List<String> playerNames;

    @BeforeAll
    public static void setPlayerNames() {
        playerNames = List.of("pobi", "honux", "crong", "jk");
    }

    @DisplayName("참여자 이름이 null이거나 공백이 입력되면 예외가 발생한다.")
    @ParameterizedTest
    @NullAndEmptySource
    void occurExceptionIfPlayerNameIsNullOrBlank(String name) {
        assertThatThrownBy(() -> InputValidator.validatePlayerName(playerNames, name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InputValidator.ERROR_PLAYER_NAME_IS_NULL_OR_BLANK);
    }

    @DisplayName("참여자 이름이  참여자 목록에 없는 이름이면 예외가 발생한다.")
    @Test
    void occurExceptionIfPlayerNameIsNotExisted() {
        String name = "name";
        assertThatThrownBy(() -> InputValidator.validatePlayerName(playerNames, name))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(InputValidator.ERROR_PLAYER_NAME_IS_NOT_EXISTED);
    }
}
