package tests.functionalTests;

import baseTest.Start;
import com.codeborne.selenide.WebDriverRunner;
import data.dataProvider.DataProviderClass;
import data.factory.models.InnerUser;
import data.inputReader.InnerUserFactory;
import database.DatabaseMethods;
import exceptions.PageTypeException;
import org.openqa.selenium.logging.LogEntries;
import org.testng.annotations.Test;
import pages.defaultPages.LoginPage;
import pages.defaultPages.UsersAndGroupsPage;
import webApplication.ApplicationRoute;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import static pages.defaultPages.LoginPage.loginByAdmin;

public class TestTest extends Start {

    @Test(enabled = false)
    public void TestingFunc() throws PageTypeException, IOException, SQLException {
        //проверка экспорта эксель файла успешно
        InnerUserFactory innerUserFactory = new InnerUserFactory("src/test/java/data/inputReader/invalid_user_out.xlsx");
        ArrayList<InnerUser> innerUserList = new ArrayList<>();
        innerUserFactory.userGeneration(innerUserList);
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        for(InnerUser innerUser : innerUserList){
            usersAndGroupsPage.createUser(innerUser.getName(), innerUser.getLastname(), innerUser.getLogin(),
                    innerUser.getPassword(), innerUser.getAddress(), innerUser.getZipCode(), innerUser.getPhone(),
                    innerUser.getIsAdmine(), innerUser.getIsEmployee(), innerUser.getFax(), innerUser.getEmail(), innerUser.getCity());

        }
        //DatabaseMethods databaseMethods = new DatabaseMethods();
        //databaseMethods.getAllUsers();
    }

    @Test(dataProvider = "validUsers", dataProviderClass = DataProviderClass.class)
    public void ValidUsersRegistration(InnerUser user) throws IOException, PageTypeException {
        // залогиниться через рут
        // создать пользователя
        // запомнить пароль и логин которые вводились при регистрации
        // выйти из аккаунта
        // войти в аккаунт
        // таким образом мы проверяем, что регистрация прошла успешно
//        LogEntries logEntries = (LogEntries) WebDriverRunner.getWebDriver().manage().logs();
        loginByAdmin();
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.createUser(user.getName(), user.getLastname(), user.getLogin(), user.getPassword(),
                user.getAddress(), user.getZipCode(), user.getPhone(), user.getIsAdmine(), user.getIsEmployee(),
                user.getFax(), user.getEmail(), user.getCity());

        LoginPage loginPage = ApplicationRoute.logOut();
        String username = user.getLogin();
        String password = user.getPassword();
        loginPage.login(username, password);

      //  System.out.println(logEntries);

    }
}