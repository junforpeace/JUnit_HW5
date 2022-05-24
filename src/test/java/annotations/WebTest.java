package annotations;

import com.codeborne.selenide.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;
import static org.openqa.selenium.By.partialLinkText;

public class WebTest {

    @ValueSource(strings = {
            "SQL",
            "Python",
            "Java"
    })

    @ParameterizedTest(name = "Check search courses {0}")
    void anySearchTest(String testData) {
        //Precondition
        Selenide.open("https://stepik.org/catalog");

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

    @ParameterizedTest(name = "Проверка по слову {0}, ожидается {1}")
    void anySearchCSVTest(String testData, String testResult) {
        //Precondition
        Selenide.open("https://stepik.org/catalog");

        //Steps
        $(".search-form__input").setValue(testData).pressEnter();
        $$(".catalog-block__content")
                .find(Condition.text(testResult))
                .has(text(testData));


    }

    static Stream<Arguments> methodSourceExampleTest() {
        return Stream.of(
                Arguments.of("first string", List.of(1, 2, 3, 4, 5, 6)),
                Arguments.of("second string", List.of(789, 10034))
        );

    }

    @MethodSource("methodSourceExampleTest")
    @ParameterizedTest
    void methodSourceExampleTest(String first, List<Integer> second) {
        System.out.println(first + " and tralala" + second);
    }
}