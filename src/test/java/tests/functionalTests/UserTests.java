package tests.functionalTests;

import baseTest.Start;
import data.dataProvider.DataProviderClass;
import data.database.DatabaseMethods;
import data.factory.models.InnerUser;
import data.inputReader.InnerUserFactory;
import exceptions.PageTypeException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.defaultPages.LoginPage;
import pages.defaultPages.UsersAndGroupsPage;
import webApplication.ApplicationRoute;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static baseTest.CommonMethods.loginByAdmin;


public class UserTests extends Start {

    // нужна стартовая авторизация
    @Test(enabled = false)
    public void TestingFunc() throws PageTypeException, IOException, SQLException {
        //проверка экспорта эксель файла успешно
        loginByAdmin();
        InnerUserFactory innerUserFactory = new InnerUserFactory("src/test/java/data/inputReader/valid_user_out.xlsx");
        ArrayList<InnerUser> innerUserList = new ArrayList<>();
        innerUserFactory.userGeneration(innerUserList);
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        for(InnerUser innerUser : innerUserList){
            usersAndGroupsPage.createUser(innerUser.getName(), innerUser.getLastname(), innerUser.getLogin(),
                    innerUser.getPassword(), innerUser.getAddress(), innerUser.getZipCode(), innerUser.getPhone(),
                    innerUser.getIsAdmine(), innerUser.getIsEmployee(), innerUser.getFax(), innerUser.getEmail(), innerUser.getCity());

        }
        DatabaseMethods databaseMethods = new DatabaseMethods();
        databaseMethods.getAllUsers();
    }

    // не нужна стартовая авторизация
    @Test(enabled = false, dataProvider = "validUsersData", dataProviderClass = DataProviderClass.class)
    public void CanCreateValidUserAndLoginHim(InnerUser user) throws IOException, PageTypeException {
        // залогиниться через рут
        // создать пользователя
        // запомнить пароль и логин которые вводились при регистрации
        // выйти из аккаунта
        // войти в аккаунт
        // успех теста проверяем отсутсвием надписи об ошибке
        // таким образом мы проверяем, что регистрация прошла успешно
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.createUser(user.getName(), user.getLastname(), user.getLogin(), user.getPassword(),
                user.getAddress(), user.getZipCode(), user.getPhone(), user.getIsAdmine(), user.getIsEmployee(),
                user.getFax(), user.getEmail(), user.getCity());
        ApplicationRoute.getAndOpenUsersAndGroupsPage();
        loginPage.logout();
        String username = user.getLogin(), password = user.getPassword();
        loginPage.login(username, password);
        ApplicationRoute.getAndOpenUsersAndGroupsPage();
        // loginPage.logout();
    }

    @Test(enabled = false, dataProvider = "invalidUsers", dataProviderClass = DataProviderClass.class)
    public void CantCreateInvalidUserAndLoginHim(InnerUser user) throws IOException, PageTypeException {
        // залогиниться через рут
        // создать пользователя не получится
        // запомнить пароль и логин которые вводились при регистрации
        // выйти из аккаунта
        // попытка войти в аккаунт некорректного пользователя
        // успех теста проверяем наличием надписи об ошибке
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.createUser(user.getName(), user.getLastname(), user.getLogin(), user.getPassword(),
                user.getAddress(), user.getZipCode(), user.getPhone(), user.getIsAdmine(), user.getIsEmployee(),
                user.getFax(), user.getEmail(), user.getCity());
        usersAndGroupsPage.errorLabelExist();
        ApplicationRoute.getAndOpenUsersAndGroupsPage();
        loginPage.logout();
        String username = user.getLogin(), password = user.getPassword();
        loginPage.login(username, password);
        ApplicationRoute.getAndOpenUsersAndGroupsPage();
        // loginPage.logout();
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
        usersAndGroupsPage.errorLabelExist();
        String login = user.getLogin();
        DatabaseMethods databaseMethods = new DatabaseMethods();
        Assert.assertFalse(databaseMethods.isUserExist(login));
    }

    @Test(enabled = false)
    public static void login_logout() throws PageTypeException {
        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
        loginPage.loginByAdmin();
        ApplicationRoute.getAndOpenUsersAndGroupsPage();
        loginPage.logout();
    }

}
