import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.Configuration;
import data.Locale;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;


import java.util.List;
import java.util.stream.Stream;

public class SelenideTest {

    static Stream<Arguments> SelenideCheckButtonsLanguage() {
        return Stream.of(
                Arguments.of(Locale.EN, List.of("Quick start", "Docs","FAQ", "Blog", "Javadoc", "Users", "Quotes")),
                Arguments.of(Locale.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Для Locale {0} отображаются кнопки {1} ")
    @Tag("BLOCKER")
    void SelenideCheckButtonsLanguage(
            Locale locale,
            List<String> buttons
    ) {
        Configuration.pageLoadTimeout = 1000000;
        open("https://selenide.org/");
        $$("#languages a").find(text(locale.name())).click();
        $$(".main-menu-pages a").filter(visible).shouldHave(texts(buttons));
    }
}
