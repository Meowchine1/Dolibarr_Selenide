package baseTest;

import browser.Browser;
import browser.BrowserPool;
import browser.BrowserType;
import data.database.DatabaseMethods;
import exceptions.BrowserTypeException;
import exceptions.PageTypeException;
import exceptions.XmlConfigureException;
import org.testng.annotations.*;
import utils.StringToEnum;
import java.io.IOException;
import java.sql.SQLException;
import static baseTest.CommonMethods.loginByAdmin;
import static baseTest.CommonMethods.openLoginPage;

public class Start {
    /* public static void main(String[] args) throws SQLException, IOException {
        DatabaseMethods databaseMethods = new DatabaseMethods();
        databaseMethods.setBackup();
    }
    */

    private Browser instance;
    @BeforeClass
    @Parameters({"BROWSER", "AUTHORIZATION"})
    public void start(String browser, boolean authorization) throws XmlConfigureException, BrowserTypeException, PageTypeException {
        BrowserType type = StringToEnum.convertBrowserType(browser);
        instance = BrowserPool.getInstance(type);
        instance.initDriver();
        if(authorization){
            loginByAdmin();
        }
        else{
            openLoginPage();
        }
    }
    @AfterMethod
    public void clearCookies(){
        instance.clearCoolies();
    }
    @AfterSuite
    public void tearDown() throws SQLException, IOException {
        DatabaseMethods databaseMethods = new DatabaseMethods();
        databaseMethods.setBackup();
        databaseMethods.closeConnection();
        //instance.close();
    }
}
