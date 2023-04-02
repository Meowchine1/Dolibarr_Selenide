package pages.defaultPages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import config.Hrefs;
import exceptions.PageTypeException;
import org.openqa.selenium.By;
import pages.Page;
import webApplication.ApplicationRoute;

import static com.codeborne.selenide.Selenide.$;

public class UsersAndGroupsPage extends Page  {

    //CREATE USER FIELDS
    private static final SelenideElement
    LASTNAME_INPUT_FIELD = $(By.xpath("//input[@name='lastname']")),
    FIRSTNAME_INPUT_FIELD = $(By.xpath("//input[@name='firstname']")),
    LOGIN_INPUT_FIELD = $(By.xpath("//input[@name='login']")),
    IS_ADMIN_SELECTOR = $("#admin"),
    IS_EMPLOYEE_CHECKBOX = $(By.xpath("//input[@name='employee']")),
    PASSWORD_INPUT_FIELD = $(By.xpath("//input[@name='password']")),
    ADDRESS_TEXTAREA = $(By.xpath("//textarea[@name='address']")),
    ZIPCODE_INPUT_FIELD = $(By.xpath("//input[@name='zipcode']")),
    CITY_INPUT_FIELD = $(By.xpath("//input[@name='town']")),
    PHONE_INPUT_FIELD = $(By.xpath("//input[@name='user_mobile']")),
    FAX_INPUT_FIELD = $(By.xpath("//input[@name='office_fax']")),
    EMAIL_INPUT_FIELD = $(By.xpath("//input[@name='email']")),
    SAVE_USER_BUTTON = $(By.xpath("//input[@name='save']"));


    public UsersAndGroupsPage(String href) {
        super(href);
    }

    public UsersAndGroupsPage getUserList() {
        Selenide.open(Hrefs.USER_LIST_HREF);

        return null;
    }

    public UsersAndGroupsPage getGroupList() {
        Selenide.open(Hrefs.GROUP_LIST_HREF);
        return null;
    }

    public UsersAndGroupsPage createUser(String name, String lastname, String login, String password,
                                         String address, String zipcode, String phone, Boolean isAdmin, Boolean isEmployee,
                                         String fax, String email, String city) throws PageTypeException {
        Selenide.open(Hrefs.CREATE_USER_HREF);
        LASTNAME_INPUT_FIELD.setValue(lastname);
        FIRSTNAME_INPUT_FIELD.setValue(name);
        LOGIN_INPUT_FIELD.setValue(login);
        setIsAdmin(isAdmin);
        setIsEmployee(isEmployee);
        PASSWORD_INPUT_FIELD.setValue(password);
        ADDRESS_TEXTAREA.setValue(address);
        ZIPCODE_INPUT_FIELD.setValue(zipcode);
        CITY_INPUT_FIELD.setValue(city);
        PHONE_INPUT_FIELD.setValue(phone);
        FAX_INPUT_FIELD.setValue(fax);
        EMAIL_INPUT_FIELD.setValue(email);
        SAVE_USER_BUTTON.click();
        return ApplicationRoute.getUsersAndGroupsPage();
    }

    private void setIsAdmin(Boolean value){  // не работает
        String selectValue = value ? "1" : "0";
        IS_ADMIN_SELECTOR.selectOptionByValue(selectValue);
    }

    private void setIsEmployee(Boolean value){
        if (value) {
            IS_EMPLOYEE_CHECKBOX.click();
        }
    }

    public UsersAndGroupsPage createGroup() {
        Selenide.open(Hrefs.CREATE_GROUP_HREF);
        return null;
    }

    public UsersAndGroupsPage updateUser() {
        Selenide.open(Hrefs.USER_LIST_HREF);
        return null;
    }

    public UsersAndGroupsPage updateGroup() {
        Selenide.open(Hrefs.GROUP_LIST_HREF);
        return null;
    }

    public UsersAndGroupsPage deleteUser() {
        Selenide.open(Hrefs.USER_LIST_HREF);
        return null;
    }

    public UsersAndGroupsPage deleteGroup() {
        Selenide.open(Hrefs.GROUP_LIST_HREF);
        return null;
    }

    public UsersAndGroupsPage getProperties() {
        return null;
    }
}
