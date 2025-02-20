package qna.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserTest {
    public static final User JAVAJIGI = new User(1L, "javajigi", "password", "name", "javajigi@slipp.net");
    public static final User SANJIGI = new User(2L, "sanjigi", "password", "name", "sanjigi@slipp.net");
    public static final User JENNIE = new User("jennie", "password", "name", "jennie@hello.com");
    public static final User SOOKI = new User("sooki", "password", "name", "sooki@hello.com");
    

    @Autowired
    private UserRepository user;

    @Test
    @DisplayName("저장됐는지 확인")
    void 저장() {
        // Given
        User expected = new User("jennie", "password", "jennie", "jennie@slipp.net");
        
        // When
        User actual = user.save(expected);
        
        // Then
        assertAll(() -> assertThat(actual).isEqualTo(expected),
                () -> assertThat(actual.getId()).isNotNull(),
                () -> assertThat(actual.getId()).isEqualTo(expected.getId()));
    }

    @Test
    @DisplayName("아이디로 조회")
    void 아이디로_조회() {
        // Given
        User expected = new User("jennie", "password", "jennie", "jennie@slipp.net");
        user.save(expected);
        
        // When
        Optional<User> actual = user.findById(expected.getId());
        
        // Then
        assertAll(() -> assertThat(actual.isPresent()).isTrue(), 
                () -> assertThat(actual.orElse(null)).isEqualTo(expected));
    }

    @Test
    @DisplayName("삭제됐는지 확인")
    void 삭제() {
        // Given
        User expected = new User("jennie", "password", "jennie", "jennie@slipp.net");
        user.save(expected);
        
        // When
        user.delete(expected);
        
        // Then
        Optional<User> actual = user.findById(expected.getId());
        assertThat(actual.isPresent()).isFalse();
    }
}
