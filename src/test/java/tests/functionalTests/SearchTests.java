package tests.functionalTests;
import baseTest.Start;
import data.dataProvider.DataProviderClass;
import exceptions.PageTypeException;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.defaultPages.UsersAndGroupsPage;
import webApplication.ApplicationRoute;

public class SearchTests extends Start {
    @Test(dataProvider = "searchUserName", dataProviderClass = DataProviderClass.class)
    public void validSearchByName(String name) throws PageTypeException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .getUserList()
                .setSearchFirstnameField(name)
                .getSearchResults();
        Assert.assertTrue(usersAndGroupsPage.searchByName(name));
    }

    @Test(dataProvider = "searchUserSurname", dataProviderClass = DataProviderClass.class)
    public void validSearchBySurname(String surname) throws PageTypeException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .getUserList()
                .setSearchLastnameField(surname);
        Assert.assertTrue(usersAndGroupsPage.searchBySurname(surname));
    }
    @Test(dataProvider = "searchUserLogin", dataProviderClass = DataProviderClass.class)
    public void validSearchByLogin(String login) throws PageTypeException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .getUserList()
                .setSearchLoginField(login);
        Assert.assertTrue(usersAndGroupsPage.searchByLogin(login));

    }
    @Test(dataProvider = "searchUserPhone", dataProviderClass = DataProviderClass.class)
    public void validSearchByPhone(String phone) throws PageTypeException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .getUserList()
                .setSearchPhoneField(phone);
        Assert.assertTrue(usersAndGroupsPage.searchByPhone(phone));
    }




}
