package guru.qa.junit;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

@DisplayName("Тесты получения данных ValueSource")
public class SourceTest {
    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
        open("https://www.bing.com/?form=REDIRERR");
        $("#bnp_btn_accept").click();
    }

    @BeforeEach
    public void setUp() {
        open("https://www.bing.com/?form=REDIRERR");
    }

    @ValueSource(strings={
            "github", "docker"
    })
    @ParameterizedTest(name = "Успешная передача параметра {0}") // тег ставится вместо @Test //{0}- порядковый номер аргумента передаваемого в метод
    public void searchSimple(String testData) {
        $(".tooltip textarea").setValue(testData).pressEnter();
        $("#b_results").shouldHave(text(testData));
    }
}
