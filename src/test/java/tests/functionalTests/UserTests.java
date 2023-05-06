package tests.functionalTests;

import baseTest.Start;
import data.dataProvider.DataProviderClass;
import data.database.DatabaseMethods;
import data.factory.models.InnerUser;
import exceptions.PageTypeException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.defaultPages.LoginPage;
import pages.defaultPages.UsersAndGroupsPage;
import webApplication.ApplicationRoute;
import java.io.IOException;
import java.sql.SQLException;


public class UserTests extends Start {

    // нужна стартовая авторизация
    @Test(enabled = true, dataProvider = "validUsers", dataProviderClass = DataProviderClass.class)
    public void _CreateUsers(InnerUser user) throws PageTypeException, IOException, SQLException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.createUser(user.getName(), user.getLastname(), user.getLogin(), user.getPassword(),
                user.getAddress(), user.getZipCode(), user.getPhone(), user.getIsAdmine(), user.getIsEmployee(),
                user.getFax(), user.getEmail(), user.getCity());
    }

    // не нужна стартовая авторизация
    @Test(enabled = false, dataProvider = "validUsers", dataProviderClass = DataProviderClass.class)
    public void CreateValidUserAndLoginHim(InnerUser user) throws IOException, PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.createUser(user.getName(), user.getLastname(), user.getLogin(), user.getPassword(),
                user.getAddress(), user.getZipCode(), user.getPhone(), user.getIsAdmine(), user.getIsEmployee(),
                user.getFax(), user.getEmail(), user.getCity());
        ApplicationRoute.getAndOpenUsersAndGroupsPage();
        logoutAndLogin(user.getLogin(), user.getPassword());
        loginPage.errorLabelIsNotExist();
    }

    @Test(enabled = false, dataProvider = "invalidUsers", dataProviderClass = DataProviderClass.class)
    public void CantCreateInvalidUserAndLoginHim(InnerUser user) throws IOException, PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.createUser(user.getName(), user.getLastname(), user.getLogin(), user.getPassword(),
                user.getAddress(), user.getZipCode(), user.getPhone(), user.getIsAdmine(), user.getIsEmployee(),
                user.getFax(), user.getEmail(), user.getCity());
        usersAndGroupsPage.createUserErrorLabelExist();
        ApplicationRoute.getAndOpenUsersAndGroupsPage();
        logoutAndLogin(user.getLogin(), user.getPassword());
        loginPage.errorLabelIsExist();
    }

    @Test(enabled = false, dataProvider = "validUsers", dataProviderClass = DataProviderClass.class)
    public void ValidUserIsInDB(InnerUser user) throws PageTypeException, SQLException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.createUser(user.getName(), user.getLastname(), user.getLogin(), user.getPassword(),
                user.getAddress(), user.getZipCode(), user.getPhone(), user.getIsAdmine(), user.getIsEmployee(),
                user.getFax(), user.getEmail(), user.getCity());
        String login = user.getLogin();
        DatabaseMethods databaseMethods = new DatabaseMethods();
        Assert.assertTrue(databaseMethods.isUserExist(login));
    }

    @Test(enabled = false, dataProvider = "invalidUsers", dataProviderClass = DataProviderClass.class)
    public void InvalidUserIsNotInDB(InnerUser user) throws PageTypeException, SQLException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.createUser(user.getName(), user.getLastname(), user.getLogin(), user.getPassword(),
                user.getAddress(), user.getZipCode(), user.getPhone(), user.getIsAdmine(), user.getIsEmployee(),
                user.getFax(), user.getEmail(), user.getCity());
        usersAndGroupsPage.createUserErrorLabelExist();
        String login = user.getLogin();
        DatabaseMethods databaseMethods = new DatabaseMethods();
        Assert.assertFalse(databaseMethods.isUserExist(login));
    }


    @Test(dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void TurnOffUser(InnerUser innerUser) throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        Assert.assertTrue(usersAndGroupsPage
                .openUserListPage()
                .turnOffUser(innerUser.getLogin()));
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());
        loginPage.errorLabelIsExist();
    }

    @Test(dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void TurnOnUser(InnerUser innerUser) throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        Assert.assertTrue(usersAndGroupsPage
                .openUserListPage()
                .turnOnUser(innerUser.getLogin()));
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());
        loginPage.errorLabelIsNotExist();
    }


    @Test(dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCanWatchOthersData(InnerUser innerUser) throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        Assert.assertTrue(usersAndGroupsPage
                .openUserListPage()
                .allowWatchUserData(innerUser.getLogin()));
        // это была подготовка
        //
        //теперь соответсвущее действие
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());
        ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.showUserList();


    }

    @Test(dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCannotWatchOthersData(InnerUser innerUser) throws PageTypeException {

        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        Assert.assertTrue(usersAndGroupsPage
                .openUserListPage()
                .allowWatchUserData(innerUser.getLogin()));
        //
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());
    }

    @Test(dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCanCreateUser(InnerUser innerUser) throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        Assert.assertTrue(usersAndGroupsPage
                .openUserListPage()
                .allowCreateUser(innerUser.getLogin()));
        //
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());

    }

    @Test(dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCannotCreateUser(InnerUser innerUser) throws PageTypeException {


    }
    @Test(dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCanDeleteUsers(InnerUser innerUser) throws PageTypeException {

        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        Assert.assertTrue(usersAndGroupsPage
                .openUserListPage()
                .allowDeleteUser(innerUser.getLogin()));
        //
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());
    }

    @Test(dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCannotDeleteUsers(InnerUser innerUser) throws PageTypeException {

        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        Assert.assertTrue(usersAndGroupsPage
                .openUserListPage()
                .allowDeleteUser(innerUser.getLogin()));
        //
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());
    }

    @Test(dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCanChangePassword(InnerUser innerUser) throws PageTypeException {

        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        Assert.assertTrue(usersAndGroupsPage
                .openUserListPage()
                .allowChangeOwnPassword(innerUser.getLogin()));
        //
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());
    }

    @Test(dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCannotChangePassword(InnerUser innerUser) throws PageTypeException {


    }





    @Test(dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void DeleteUser(InnerUser innerUser) throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        Assert.assertTrue(usersAndGroupsPage
                .openUserListPage()
                .deleteUser(innerUser.getLogin()));
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());
        loginPage.errorLabelIsExist();
    }


    private void logoutAndLogin(String login, String password) throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.logout();
        loginPage.login(login, password);
    }

}
