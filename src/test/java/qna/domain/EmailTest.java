package qna.domain;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.stream.IntStream;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class EmailTest {
    @Test
    @DisplayName("잘못된 값이 들어갈때 오류 확인")
    void 데이터_확인() {
        StringBuilder overSizeText = new StringBuilder();
        IntStream.range(0, Email.EMAIL_MAX_SIZE+1).forEach(i -> overSizeText.append("a"));
        
        assertAll(() -> assertThrows(IllegalArgumentException.class, () -> {Email.of("");}),
                () -> assertThrows(IllegalArgumentException.class, () -> {Email.of(overSizeText.toString());}));
    }
}
