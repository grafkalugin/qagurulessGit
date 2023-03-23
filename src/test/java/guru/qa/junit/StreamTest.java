package guru.qa.junit;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.junit.data.StateAndCity;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.List;
import java.util.stream.Stream;
import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

@DisplayName("Тесты получения данных через Stream")
public class StreamTest {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://demoqa.com/automation-practice-form");
        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");
    }

    static Stream<Arguments> paramStreamTest(){
        return Stream.of(
                Arguments.of(StateAndCity.NCR, List.of("Delhi", "Gurgaon", "Noida")),
                Arguments.of(StateAndCity.Rajasthan, List.of("Jaipur", "Jaiselmer"))
                );
    }

    @MethodSource()
    @ParameterizedTest(name = "Проверка получения городов {1} по региону {0}")
    public void paramStreamTest(StateAndCity state, List<String> expectedCity) {
        $("#state").click();
        $(byText(state.name())).click();
        $("#city").click();
        $$(".css-11unzgr div").shouldHave(texts(expectedCity));
    }
}