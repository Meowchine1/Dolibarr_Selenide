package tests.functionalTests;

import baseTest.Start;
import com.github.javafaker.Faker;
import data.dataProvider.DataProviderClass;
import data.database.DatabaseMethods;
import exceptions.PageTypeException;
import models.InnerUser;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.defaultPages.LoginPage;
import pages.defaultPages.UsersAndGroupsPage;
import webApplication.ApplicationRoute;
import java.io.IOException;
import java.sql.SQLException;



public class UserTests extends Start {

    // можно автоматически стартовать
    // это временный тестовый метод создает небольшое кол-во пользователей, которые
    // тестируются в Search test
    @Test(enabled = true, dataProvider = "testvalidUsers", dataProviderClass = DataProviderClass.class)
    public void CreateUsers(InnerUser user) throws PageTypeException{
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.createUser(user);
    }

    // нельзя автоматически стартовать
    @Test(enabled = false, dataProvider = "validUsers", dataProviderClass = DataProviderClass.class)
    public void CreateValidUserAndLoginHim(InnerUser user) throws PageTypeException, SQLException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.createUser(user);
        ApplicationRoute.getAndOpenUsersAndGroupsPage();
        logoutAndLogin(user.getLogin(), user.getPassword()); // проверка того, что пользователь зареган
        // а значит он есть в бд
        // теперь проверка что в бд инфа соответсвует введенной через UI
        DatabaseMethods databaseMethods = new DatabaseMethods();
        boolean test1 = databaseMethods.testParamValue("llx_user", "email", user.getEmail(), "login", user.getLogin()),
                test2 = databaseMethods.testParamValue("llx_user", "lastname", user.getLastname(), "login", user.getLogin()),
                test3 = databaseMethods.testParamValue("llx_user", "firstname", user.getName(), "login", user.getLogin()),
                test4 = databaseMethods.testParamValue("llx_user", "office_fax", user.getFax(), "login", user.getLogin());
        Assert.assertTrue(test1 && test2 && test3 && test4);

    }

    @Test(enabled = false, dataProvider = "invalidUsers", dataProviderClass = DataProviderClass.class)
    public void CantCreateInvalidUser(InnerUser user) throws IOException, PageTypeException, SQLException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.createUser(user);
        usersAndGroupsPage.createUserErrorLabelExist();
        // убедимся, что в бд нет записи
        DatabaseMethods databaseMethods = new DatabaseMethods();
        Assert.assertFalse(databaseMethods.isUserExist(user.getLogin()));
    }

    // зависимый тест от
    @Test(enabled = false, dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void TurnOffUser(InnerUser innerUser) throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .openUserListPage()
                .turnOffUser(innerUser.getLogin());
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());
        loginPage.errorLabelIsExist(); // assertion
    }

    // зависимый от предыдущего теста тест
    @Test(enabled = false, dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void TurnOnUser(InnerUser innerUser) throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .openUserListPage()
                .turnOnUser(innerUser.getLogin());
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());
        loginPage.errorLabelIsNotExist();
    }

    @Test(enabled = true, dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCanWatchOthersData(InnerUser innerUser) throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage         // подготовка
                .openUserListPage()
                .allowWatchUserData(innerUser.getLogin());
        //
        //теперь соответсвущее действие
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());
        ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.canWatchUsers();
    }

    @Test(enabled = false, dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCannotWatchOthersData(InnerUser innerUser) throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .openUserListPage()
                .allowWatchUserData(innerUser.getLogin());
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());

        // проверить, что пользователь не может посмотреть других пользователей и группы
        ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.cantWatchUsers();
    }

    @Test(enabled = false, dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCanCreateUser(InnerUser innerUser) throws PageTypeException, SQLException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .openUserListPage()
                .allowCreateUser(innerUser.getLogin());
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());

        //проверим доступ к созданию пользователя
        ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.canAddNewUser();

        //создадим пользователя
        InnerUser stubUser = new InnerUser();
        Faker faker = new Faker();
        String login = faker.name().username(), password = faker.regexify(".{12}");
        stubUser.setLogin(login);
        stubUser.setPassword(password);
        usersAndGroupsPage.createStubUser(stubUser);
        //Проверим бд на наличие пользователя
        DatabaseMethods databaseMethods = new DatabaseMethods();
        Assert.assertTrue(databaseMethods.isUserExist(login));

    }

    @Test(enabled = false, dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCannotCreateUser(InnerUser innerUser) throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .openUserListPage()
                .allowCreateUser(innerUser.getLogin());
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());

        //создать пользователя
        ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.cantAddNewUser();
    }

    @Test(enabled = false, dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCanDeleteUsers(InnerUser innerUser) throws PageTypeException {

        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        Assert.assertTrue(usersAndGroupsPage
                .openUserListPage()
                .allowDeleteUser(innerUser.getLogin()));
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());
    }

    @Test(enabled = false, dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCannotDeleteUsers(InnerUser innerUser) throws PageTypeException {

        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        Assert.assertTrue(usersAndGroupsPage
                .openUserListPage()
                .allowDeleteUser(innerUser.getLogin()));
        logoutAndLogin(innerUser.getLogin(), innerUser.getPassword());
    }

    @Test(enabled = false, dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
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

    @Test(enabled = false, dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
    public void UserCannotChangePassword(InnerUser innerUser) throws PageTypeException {
    }

    @Test(enabled = false, dataProvider = "oneValidUser", dataProviderClass = DataProviderClass.class)
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
