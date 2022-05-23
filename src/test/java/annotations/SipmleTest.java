package annotations;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Класс с тестами")
public class SipmleTest {


    @DisplayName("Пробуем тесты")

    @Test
    void FirstTest () {
        Assertions.assertTrue(2 < 3);
        Assertions.assertFalse(3 < 5);
        Assertions.assertAll();


    }
}
