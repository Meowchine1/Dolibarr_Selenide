package pages.defaultPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.selector.ByTagAndText;
import exceptions.PageTypeException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import pages.Page;
import webApplication.ApplicationRoute;

import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends Page {
    private static final SelenideElement
            USERNAME_INPUT_FIELD = $(By.id("username")),
            PASSWORD_INPUT_FIELD = $(By.id("password")),
            SUBMIT_BUTTON = $(By.cssSelector("[type='submit']")),
            MENU = $(".mainmenuaspan"),
            DROPDOWN_MENU = $(By.xpath(".//a[@data-toggle='dropdown']")),
            LOGOUT = $(By.xpath(".//a[@title='Выход']")),
            ERROR_LABEL = $(withTagAndText("div", "Плохо стоимости логин или пароль"));

    public LoginPage(String href) {
        super(href);
    }

    public InformationPanelPage login(String username, String password) throws PageTypeException {
        USERNAME_INPUT_FIELD.setValue(username);
        PASSWORD_INPUT_FIELD.setValue(password);
        SUBMIT_BUTTON.click();

        ERROR_LABEL.shouldNotBe(Condition.exist);

        return ApplicationRoute.getInformationPanelPage();
    }

    public void loginByAdmin() throws PageTypeException { // данные убрать
        LoginPage loginPage = ApplicationRoute.getLoginPage();
        loginPage.login("root", "8962615k");
    }

    public void errorLabelIsNotExist(){

    }

    public void errorLabelIsExist(){

    }

    public LoginPage logout() throws PageTypeException {
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.moveToElement(DROPDOWN_MENU).click().perform();
        actions.moveToElement(LOGOUT).click().perform();
        return ApplicationRoute.getLoginPage();
    }
}
