package pages.defaultPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.WebDriverRunner;
import config.AccountData;
import exceptions.PageTypeException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import pages.Page;
import webApplication.ApplicationRoute;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends Page {
    private static final SelenideElement
            USERNAME_INPUT_FIELD = $("#username"),
            PASSWORD_INPUT_FIELD = $("#password"),
            SUBMIT_BUTTON = $(By.cssSelector("[type='submit']")),
            MENU = $(".mainmenuaspan"),
            DROPDOWN_MENU = $(By.xpath(".//a[@data-toggle='dropdown']")),
            LOGOUT = $(By.xpath(".//a[@title='Выход']")),
            ERROR_LABEL = $(".error"),
            SUCCESS_LABEL = $(withText("Моя Информ-панель"));

    public LoginPage(String href) {
        super(href);
    }

    public InformationPanelPage login(String username, String password) throws PageTypeException {
        USERNAME_INPUT_FIELD.setValue(username);
        PASSWORD_INPUT_FIELD.setValue(password);
        SUBMIT_BUTTON.click();

        SUCCESS_LABEL.shouldBe(Condition.exist);

        return ApplicationRoute.getInformationPanelPage();
    }

    public void loginByAdmin() throws PageTypeException { // данные убрать
        LoginPage loginPage = ApplicationRoute.getLoginPage();
        loginPage.login(AccountData.ADMIN_LOGIN, AccountData.ADMIN_PASSWORD);
    }

    public void errorLabelIsNotExist(){
        ERROR_LABEL.shouldNotBe(Condition.exist);
    }

    public void errorLabelIsExist(){
        SUCCESS_LABEL.shouldNotBe(Condition.exist);
    }

    public LoginPage logout() throws PageTypeException {
        Actions actions = new Actions(WebDriverRunner.getWebDriver());
        actions.moveToElement(DROPDOWN_MENU).click().perform();
        actions.moveToElement(LOGOUT).click().perform();
        return ApplicationRoute.getLoginPage();
    }
}
