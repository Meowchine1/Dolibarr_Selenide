package baseTest;

import exceptions.PageTypeException;
import pages.defaultPages.LoginPage;
import webApplication.ApplicationRoute;

public class CommonMethods {

    public static void loginByAdmin() throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
    }

    public static void openLoginPage() throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();

    }

}
