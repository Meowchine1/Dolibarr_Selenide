package tests.functionalTests;

import baseTest.Start;
import data.dataProvider.DataProviderClass;
import data.factory.models.InnerUser;
import exceptions.PageTypeException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.defaultPages.UsersAndGroupsPage;
import webApplication.ApplicationRoute;

public class SearchTests extends Start {

    // зависимый тест от тестов класса UserTests
    @Test(dataProvider = "searchUser", dataProviderClass = DataProviderClass.class)
    public void validSearchByName(InnerUser innerUser) throws PageTypeException {
      UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
      usersAndGroupsPage
              .getUserList()
              .setSearchFirstnameField(innerUser.getName())
              .getSearchResults();

        Assert.assertTrue(usersAndGroupsPage.searchDataShouldBeExist());
    }

    // зависимый тест от тестов класса UserTests
    @Test(dataProvider = "searchUser", dataProviderClass = DataProviderClass.class)
    public void validSearchByLastName(InnerUser innerUser) throws PageTypeException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .getUserList()
                .setSearchLastnameField(innerUser.getLastname());
        Assert.assertTrue(usersAndGroupsPage.searchDataShouldBeExist());
    }

    // зависимый тест от тестов класса UserTests
    @Test(enabled = true, dataProvider = "searchUser", dataProviderClass = DataProviderClass.class)
    public void validSearchByLogin(InnerUser innerUser) throws PageTypeException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .getUserList()
                .setSearchLoginField(innerUser.getLogin());
        Assert.assertTrue(usersAndGroupsPage.searchDataShouldBeExist());
    }

    // зависимый тест от тестов класса UserTests
    @Test(enabled = true, dataProvider = "searchUser", dataProviderClass = DataProviderClass.class)
    public void validSearchByMail(InnerUser innerUser) throws PageTypeException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .getUserList()
                .setSearchMailField(innerUser.getEmail());
        Assert.assertTrue(usersAndGroupsPage.searchDataShouldBeExist());
    }

    // зависимый тест от тестов класса UserTests
    @Test(enabled = true, dataProvider = "searchUser", dataProviderClass = DataProviderClass.class)
    public void validSearchByPhone(InnerUser innerUser) throws PageTypeException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .getUserList()
                .setSearchPhoneField(innerUser.getPhone());
        Assert.assertTrue(usersAndGroupsPage.searchDataShouldBeExist());
    }

}
