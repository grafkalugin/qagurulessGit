package guru.qa.junit.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

// page_url = https://www.raiffeisen.ru/
public class MainPage {
    public SelenideElement localeEn = $("[href='https://www.raiffeisen.ru/en']");
    //ДЛЯ ЖИЗНИ
    //МАЛОМУ БИЗНЕСУ
    //КОРПОРАТИВНОМУ БИЗНЕСУ
    //PREMIUM

    //Consumer Loans
    //Mortgage
    //Bank cards
    //Saving and investing
    public SelenideElement localeRu = $("[data-marker='TopMenuItems.Box_0']").find(byText("ru"));
    public SelenideElement localeEng = $("[data-marker='TopMenuItems.Box_0']").find(byText("ENG"));

    public ElementsCollection topBar = $$("[data-marker='HeaderWrap.Box']");

    public SelenideElement forLifeButton = $("[data-marker='HeaderWrap.Box']").find(byText("Для жизни"));
    public SelenideElement littleBusiness = $("[data-marker='HeaderWrap.Box']").find(byText("Малому бизнесу"));
    public SelenideElement toolsMenu = $x("//div[@data-test='main-menu-item' and @data-test-marker = 'Developer Tools']");
    public SelenideElement searchButton = $("[data-test='site-header-search-action']");
}
