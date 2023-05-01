package pages.defaultPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import config.Hrefs;
import exceptions.PageTypeException;
import org.openqa.selenium.By;
import pages.Page;
import webApplication.ApplicationRoute;

import static com.codeborne.selenide.Selenide.$;

public class UsersAndGroupsPage extends Page  {
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
    SAVE_USER_BUTTON = $(By.xpath("//input[@name='save']")),
    ERROR_LABEL = $(".jnotify-message"),
    USER_RIGHTS_LABEL = $("#rights");

    //SEARCH FIELDS
    public static final SelenideElement
    SEARCH_LOGIN_INPUT_FIELD = $(By.xpath(".//input[@name='search_login']")),
    SEARCH_LASTNAME_INPUT_FIELD = $(By.xpath(".//input[@name='search_lastname']")),
    SEARCH_FIRSTNAME_INPUT_FIELD = $(By.xpath(".//input[@name='search_firstname']")),
    SEARCH_PHONE_INPUT_FIELD = $(By.xpath(".//input[@name='search_phonemobile']")),
    SEARCH_MAIL_INPUT_FIELD = $(By.xpath(".//input[@name='search_email']")),
    SEARCH_BUTTON = $(By.xpath(".//button[@name='button_search_x']"));

    //TABLE
    private static int table_iterator = 3;
    private static SelenideElement TABLE_ROW = $(By.xpath(".//table//tr[" + table_iterator + "]")),
    TABLE_ROW_LOGIN = $(By.xpath(".//table//tr[" + table_iterator + "]//span[@class='nopadding usertext']")),
    TABLE_ROW_SURNAME = $(By.xpath(".//table//tr[" + table_iterator + "]//td[@class='tdoverflowmax150'][1]")),
    TABLE_ROW_NAME = $(By.xpath(".//table//tr[" + table_iterator + "]//td[@class='tdoverflowmax150'][2]")),
    TABLE_ROW_PHONE = $(By.xpath(".//table//tr[" + table_iterator + "]//td[@class='tdoverflowmax125'][3]")),
            START_TABLE_HREF = $(By.xpath(".//table//tr["+table_iterator+"]//a"));


    public boolean searchByLogin(String login){
        TABLE_ROW.shouldBe(Condition.exist);
        return TABLE_ROW_LOGIN.text().equals(login);
    }
    public boolean searchByName(String name){
        TABLE_ROW.shouldBe(Condition.exist);
        return TABLE_ROW_NAME.text().equals(name);
    }
    public boolean searchBySurname(String surname){
        TABLE_ROW.shouldBe(Condition.exist);
        return TABLE_ROW_SURNAME.text().equals(surname);
    }
    public boolean searchByPhone(String phone){
        TABLE_ROW.shouldBe(Condition.exist);
        return TABLE_ROW_PHONE.text().equals(phone);
    }

    public UsersAndGroupsPage(String href) {
        super(href);
    }

    public UsersAndGroupsPage setSearchLastnameField(String value) throws PageTypeException {
        SEARCH_LASTNAME_INPUT_FIELD.val(value);
        return ApplicationRoute.getUsersAndGroupsPage();
    }
    public UsersAndGroupsPage setSearchFirstnameField(String value) throws PageTypeException {
        SEARCH_FIRSTNAME_INPUT_FIELD.val(value);
        return ApplicationRoute.getUsersAndGroupsPage();
    }
    public UsersAndGroupsPage setSearchPhoneField(String value) throws PageTypeException {
        SEARCH_PHONE_INPUT_FIELD.val(value);
        return ApplicationRoute.getUsersAndGroupsPage();
    }
    public UsersAndGroupsPage setSearchMailField(String value) throws PageTypeException {
        SEARCH_MAIL_INPUT_FIELD.val(value);
        return ApplicationRoute.getUsersAndGroupsPage();
    }
    public UsersAndGroupsPage setSearchLoginField(String value) throws PageTypeException {
        SEARCH_LOGIN_INPUT_FIELD.val(value);
        return ApplicationRoute.getUsersAndGroupsPage();
    }

    public UsersAndGroupsPage getSearchResults() throws PageTypeException {
        SEARCH_BUTTON.click();
        return ApplicationRoute.getUsersAndGroupsPage();
    }
    public void getListInf(){

        while(TABLE_ROW.is(Condition.exist)){
            System.out.println(START_TABLE_HREF.text());
            table_iterator +=1;
            START_TABLE_HREF = $(By.xpath(".//table//tr["+table_iterator+"]//a"));
            TABLE_ROW = $(By.xpath(".//table//tr[" + table_iterator + "]"));
        }
        table_iterator = 3;
    }
    public UsersAndGroupsPage getUserList() throws PageTypeException {
        Selenide.open(Hrefs.USER_LIST_HREF);

        return ApplicationRoute.getUsersAndGroupsPage();
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

    public void showUserList(){
        Selenide.open(Hrefs.USER_LIST_HREF);
    }
    public void showGroupList(){
        Selenide.open(Hrefs.GROUP_LIST_HREF);
    }

    public void errorLabelExist(){
        ERROR_LABEL.shouldBe(Condition.visible);
    }
}
