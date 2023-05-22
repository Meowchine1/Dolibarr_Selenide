package browser;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.openqa.selenium.WebDriver;

public class Browser {
    private BrowserType browserType;
    public Browser(BrowserType browserType) {
        this.browserType = browserType;
    }

    public void initDriver(){
        Configuration.browser = browserType.toString().toLowerCase();
        Configuration.pageLoadStrategy = "eager";
        Configuration.browserSize = "1920x1080";
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
        Configuration.driverManagerEnabled = true;
        Configuration.headless = false;
        //Configuration.proxyEnabled = true;
        // remote testing
        //Configuration.driberManagerEnabled = false
        //Configuration.remote = ""
        //Configuration.holdBrowserOpen = false
    }

    public WebDriver currentDriver(){

        return WebDriverRunner.getWebDriver();
    }

    public void close(){
       currentDriver().quit();
    }

    public void clearCoolies(){
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
    }
}
