package guru.qa.junit.example;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import guru.qa.junit.Blocker;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;


@Disabled("jiraid-123456")
@DisplayName("Название сьюта (в Allure есть @Suite но эта тоже работает), можно вешать на класс используется как в junit так и в Allure")
public class MainPageTestExample2 {
    MainPage mainPage = new MainPage();

    @BeforeAll // не выносить прекондишены не будет работать при многопоточном запуске. Привязан к другому потоку
    public static void setUpAllEx() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }
    @BeforeEach
    public void setUpEx() {
        open("https://www.raiffeisen.ru/");
    }


    @Tag("Blocker") // можно создать свою аннотацию
    @ValueSource(strings={
            "Selenide", "Allure"
    })
    @ParameterizedTest (name = "Успешная передача параметра {0}") // тег ставится вместо @Test //{0}- порядковый номер аргумента передаваемого в метод
    public void searchEx(String testData) {
        mainPage.searchButton.click();
        Assertions.assertEquals(1,1,"Один равен одному");
    }



    @CsvSource(value={
            "Selenide| 1234",
            "Selenide| 1234",
    },
    delimiter = '|') // по дефолту используются запятые, можно задать свой разделитель
    @ParameterizedTest (name = "Успешная передача параметра {0}, с отображением текста {1}")
    @Tags({
            @Tag("Smoke"),
            @Tag("Web")
    })
    @Disabled("jiraid-123456") // тест работает неправильно, баг котороый пока не фиксится
    @Test
    public void toolsMenuEx(String qwery, String text) {
        mainPage.toolsMenu.click();
    }

    @Blocker // катстомый тег
    @CsvFileSource(resources= "/testdata/textBoxTest.csv")
    @ParameterizedTest (name = "Успешная передача параметра {0}, с отображением текста {1}")
    //@DisplayName("Успешная передача параметра. junit но используется в Allure") // не нужно указывать тег, если есть @ParameterizedTest
    public void navigationToAllToolsEx(String a, int b) {
        mainPage.seeAllToolsButton.click();

        $("#products-page").shouldBe(visible);
        assertEquals("All Developer Tools and Products by JetBrains", Selenide.title());
    }
}
