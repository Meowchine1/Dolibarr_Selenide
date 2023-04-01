package pages.defaultPages;

import com.codeborne.selenide.SelenideElement;
import exceptions.PageTypeException;
import org.openqa.selenium.By;
import pages.Page;
import webApplication.ApplicationRoute;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage extends Page {
    private static final SelenideElement
            USERNAME_INPUT_FIELD = $(By.id("username")),
            PASSWORD_INPUT_FIELD = $(By.id("password")),
            SUBMIT_BUTTON = $(By.cssSelector("[type='submit']")),
            FORGET_PASSWORD_BUTTON = $(By.xpath("//*[contains(text(),'Забыли')]"));///!!!russian???

    public LoginPage(String href) {
        super(href);
    }

    public InformationPanelPage login(String username, String password) throws PageTypeException {
        USERNAME_INPUT_FIELD.setValue(username);
        PASSWORD_INPUT_FIELD.setValue(password);
        SUBMIT_BUTTON.click();

        return ApplicationRoute.getInformationPanelPage();
    }
}
