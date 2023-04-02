package baseTest;

import exceptions.PageTypeException;
import pages.defaultPages.LoginPage;
import webApplication.ApplicationRoute;

public class CommonMethods {

    public static void LoginByAdmin() throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
    }

}
