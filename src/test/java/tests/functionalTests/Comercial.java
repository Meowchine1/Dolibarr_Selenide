package tests.functionalTests;
import baseTest.Start;
import data.dataProvider.DataProviderClass;
import data.database.DatabaseMethods;
import exceptions.PageTypeException;
import models.InnerUser;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.defaultPages.UsersAndGroupsPage;
import webApplication.ApplicationRoute;

import java.sql.SQLException;

public class Comercial extends Start {

    @Test(enabled = true, dataProvider = "validUsers", dataProviderClass = DataProviderClass.class)
    public void CreateUsers(InnerUser user) throws PageTypeException{
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage.createUser(user);
        Assert.assertTrue(true);
    }
    @Test(enabled = true, dataProvider = "searchUserName", dataProviderClass = DataProviderClass.class)
    public void validSearchByName(String name) throws PageTypeException, SQLException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .openUserListPage()
                .setSearchFirstnameField(name);
        //Assert.assertTrue(usersAndGroupsPage.assertionSearchByName(name));
        DatabaseMethods databaseMethods = new DatabaseMethods();
        Assert.assertEquals(usersAndGroupsPage.searchResultCount(), databaseMethods.
                dbSearchResults("llx_user", "firstname", name));

    }

    @Test(enabled = true, dataProvider = "searchUserSurname", dataProviderClass = DataProviderClass.class)
    public void validSearchBySurname(String surname) throws PageTypeException, SQLException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .openUserListPage()
                .setSearchLastnameField(surname);
       // Assert.assertTrue(usersAndGroupsPage.assertionSearchBySurname(surname));
        DatabaseMethods databaseMethods = new DatabaseMethods();
        Assert.assertEquals(usersAndGroupsPage.searchResultCount(), databaseMethods.
                dbSearchResults("llx_user", "lastname", surname));
    }
    @Test(enabled = true, dataProvider = "searchUserLogin", dataProviderClass = DataProviderClass.class)
    public void validSearchByLogin(String login) throws PageTypeException, SQLException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .openUserListPage()
                .setSearchLoginField(login);
        //Assert.assertTrue(usersAndGroupsPage.assertionSearchByLogin(login));
        DatabaseMethods databaseMethods = new DatabaseMethods();
        Assert.assertEquals(usersAndGroupsPage.searchResultCount(), databaseMethods.
                dbSearchResults("llx_user", "login", login));

    }
    @Test(enabled = true,  dataProvider = "searchUserPhone", dataProviderClass = DataProviderClass.class)
    public void validSearchByPhone(String phone) throws PageTypeException, SQLException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .openUserListPage()
                .setSearchPhoneField(phone);
        //Assert.assertTrue(usersAndGroupsPage.assertionSearchByPhone(phone));
        DatabaseMethods databaseMethods = new DatabaseMethods();
        Assert.assertEquals(usersAndGroupsPage.searchResultCount(), databaseMethods.
                dbSearchResults("llx_user", "user_mobile", phone));
    }




}
