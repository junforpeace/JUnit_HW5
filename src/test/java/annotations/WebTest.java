package annotations;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import java.util.List;
import java.util.stream.Stream;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


public class WebTest {

    @BeforeAll
    public static void openWeb() {
        Selenide.open("https://stepik.org/catalog");
    }
    @ValueSource(strings = {
            "SQL",
            "Python",
            "Java"
    })
    @DisplayName("Проверяем наличие курсов")
    @ParameterizedTest(name = "Check search courses {0}")
    void anySearchTest(String testData) {
        //Steps
        $(".search-form__input").setValue(testData).pressEnter();
        $$(".catalog-block__content")
                .find(Condition.text(testData))
                .shouldBe(visible);
    }
    @CsvSource(value = {
            "SQL | Online-курс по основам SQL",
            "Python | Для успешного прохождения курса необходимо знать основы языка Python"
    },
            delimiter = '|')
    @DisplayName("Проверка по курсам с csvsource")
    @ParameterizedTest(name = "Проверка по слову {0}, ожидается {1}")
    void anySearchCSVTest(String testData, String testResult) {
        $(".search-form__input").setValue(testData).pressEnter();
        $$(".catalog-block__content")
                .find(Condition.text(testResult))
                .has(text(testData));

    }
    static Stream<Arguments> checkAuthorsCourse() {
        return Stream.of(
                Arguments.of("ООП", List.of("Артем Егоров", "Сергей Балакирев", "Артем Егоров")),
                Arguments.of("Тестирование", List.of("Алекс Смит", "Татьяна Овчинникова", "Константин Барзаковский"))
        );

    }
    @MethodSource("checkAuthorsCourse")
    @ParameterizedTest
    void checkAuthorsCourse(String searchOption, List<String> courseAuthor) {
        $(".search-form__input").setValue(searchOption).pressEnter();

        $$(".catalog-block__content").containsAll(courseAuthor);

    }

}