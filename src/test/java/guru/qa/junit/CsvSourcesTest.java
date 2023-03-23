package guru.qa.junit;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

//@Disabled("jiraid-123456")
@DisplayName("Тесты получения данных через CSV")
public class CsvSourcesTest {

    @BeforeAll
    public static void setUpAll() {
        Configuration.browserSize = "1280x800";
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @BeforeEach
    public void setUp() {
        open("https://demoqa.com/text-box");
        Selenide.executeJavaScript("$('#fixedban').remove()");
        Selenide.executeJavaScript("$('footer').remove()");
    }

    @CsvSource(value={
            "Sasha|sasha@google.com|Address|ParAddress",
            "Lena|lena@google.com|AddressLena|ParAddressLena"
    },
    delimiter = '|')
    @ParameterizedTest (name = "Успешная передача параметра {0}, с отображением текста {1}")
    @Tags({
            @Tag("Smoke"),
            @Tag("Web")
    })

    public void textBoxTestCsvSource(String name, String email, String address, String perAddress) {
        $("#userName").setValue(name);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(address);
        $("#permanentAddress").setValue(perAddress);
        $("#submit").click();
        $("#output").shouldHave(text(name))
                .shouldHave(text(email))
                .shouldHave(text(address))
                .shouldHave(text(perAddress));
    }

    @Blocker
    @CsvFileSource(resources= "/testdata/textBoxTest.csv",
            delimiter = '|')
    @ParameterizedTest (name = "Успешная передача параметра {0}, с отображением текста {1}")
    //@DisplayName("Успешная передача параметра. junit но используется в Allure") // не нужно указывать тег, если есть @ParameterizedTest
    public void textBoxTestCsvFileSource(String name, String email, String address, String perAddress) {
        $("#userName").setValue(name);
        $("#userEmail").setValue(email);
        $("#currentAddress").setValue(address);
        $("#permanentAddress").setValue(perAddress);
        $("#submit").click();
        $("#output").shouldHave(text(name))
                .shouldHave(text(email))
                .shouldHave(text(address))
                .shouldHave(text(perAddress));
    }

    @Test
    @DisplayName("disabledTest")
    @Disabled("jiraid-123456")
    @Tag("Blocker")
    public void disabledTest(){
        System.out.println("Disabled test");
    }

}
