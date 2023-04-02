package baseTest;

import browser.Browser;
import browser.BrowserPool;
import browser.BrowserType;
import exceptions.BrowserTypeException;
import exceptions.PageTypeException;
import exceptions.XmlConfigureException;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import pages.defaultPages.LoginPage;
import utils.StringToEnum;
import webApplication.ApplicationRoute;

import static baseTest.CommonMethods.LoginByAdmin;

public class Start {
    private Browser instance;
    private BrowserType type;
    @BeforeClass
    @Parameters({"BROWSER", "AUTHORIZATION"})
    public void start(String browser, boolean authorization) throws XmlConfigureException, BrowserTypeException, PageTypeException {
        type = StringToEnum.convertBrowserType(browser);
        instance = BrowserPool.getInstance(type);
        instance.initDriver();
        if(authorization){
            LoginByAdmin();
        }
    }

    @AfterClass
    public void tearDown(){
        instance.close();
    }
}
