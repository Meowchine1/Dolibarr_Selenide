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
                .openUserListPage()
                .setSearchFirstnameField(name)
                .searchResults();
        Assert.assertTrue(usersAndGroupsPage.assertionSearchByName(name));
    }

    @Test(dataProvider = "searchUserSurname", dataProviderClass = DataProviderClass.class)
    public void validSearchBySurname(String surname) throws PageTypeException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .openUserListPage()
                .setSearchLastnameField(surname);
        Assert.assertTrue(usersAndGroupsPage.assertionSearchBySurname(surname));
    }
    @Test(dataProvider = "searchUserLogin", dataProviderClass = DataProviderClass.class)
    public void validSearchByLogin(String login) throws PageTypeException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .openUserListPage()
                .setSearchLoginField(login);
        Assert.assertTrue(usersAndGroupsPage.assertionSearchByLogin(login));

    }
    @Test(dataProvider = "searchUserPhone", dataProviderClass = DataProviderClass.class)
    public void validSearchByPhone(String phone) throws PageTypeException {
        UsersAndGroupsPage usersAndGroupsPage = ApplicationRoute.getAndOpenUsersAndGroupsPage();
        usersAndGroupsPage
                .openUserListPage()
                .setSearchPhoneField(phone);
        Assert.assertTrue(usersAndGroupsPage.assertionSearchByPhone(phone));
    }




}
