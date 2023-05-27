package tests.functionalTests;

import baseTest.Start;
import config.UserType;
import data.dataProvider.DataProviderClass;
import exceptions.PageTypeException;
import org.testng.annotations.Test;
import pages.defaultPages.LoginPage;
import pages.defaultPages.ThirdPartiesPage;
import webApplication.ApplicationRoute;

import java.io.IOException;
import java.util.Map;

//llx_societe
public class ThirdPartiesTests extends Start {
//    @Test
//    public void registerExternalUserAndLoginHim() throws PageTypeException {
//
//
//        //  выбрать валидный контакт и создать по его образу аккаунт
//        // logout
//        // войти используя данные логина и пароля аккаунта
//        Map<String, String> data = ApplicationRoute
//                .getAndOpenThirdPartiesPage()
//                .openContactList()
//                .registerContact(UserType.EXTERNAL);
//        ApplicationRoute
//                .getAndOpenLoginPage()
//                .logout()
//                .login(data.get("login"), data.get("password"));
//    }
//
//    @Test(enabled = false)
//    public void registerInnerlUserAndLoginHim() throws PageTypeException {
//
//        //  выбрать валидный контакт и создать по его образу аккаунт
//        // logout
//        // войти используя данные логина и пароля аккаунта
//Map<String, String> data = ApplicationRoute
//                .getAndOpenThirdPartiesPage()
//                .openContactList()
//                .registerContact(UserType.INNER);
//        ApplicationRoute
//                .getAndOpenLoginPage()
//                .logout()
//                .login(data.get("login"), data.get("password"));
//    }
//
//    @Test(enabled = false, dataProvider = "validUsersData", dataProviderClass = DataProviderClass.class)
//    public void CanCreateValidUserAndLoginHim(ExternalUser user) throws IOException, PageTypeException {
//        // залогиниться через рут
//
//        // запомнить пароль и логин которые вводились при регистрации
//        // выйти из аккаунта
//        // войти в аккаунт
//        // успех теста проверяем отсутсвием надписи об ошибке
//        // таким образом мы проверяем, что регистрация прошла успешно
//        LoginPage loginPage = ApplicationRoute.getAndOpenLoginPage();
//        loginPage.loginByAdmin();
//
//        // создать контрагента
//        ThirdPartiesPage thirdPartiesPage = ApplicationRoute.getAndOpenThirdPartiesPage();
//        thirdPartiesPage.createContact(user.getName(), user.getAddress(), user.getZipCode(), user.getPhone(), // create
//                user.getEmail(), user.getCity());
//
//        ApplicationRoute.getAndOpenThirdPartiesPage();
//        loginPage.logout();
//        String username = user.getLogin(), password = user.getPassword();
//        loginPage.login(username, password);
//        ApplicationRoute.getAndOpenUsersAndGroupsPage();
//
//    }
//
//    // тест проверка на то, что внешнему пользователю недоступны:
//    // списки контрагентов
//    // списки контактов
//    // списки пользователей
//    //
//    //

}
