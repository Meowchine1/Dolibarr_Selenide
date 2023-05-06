package pages.defaultPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import config.Hrefs;
import exceptions.PageTypeException;
import org.openqa.selenium.By;
import pages.Page;
import webApplication.ApplicationRoute;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

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
    CREATE_USER_ERROR_LABEL = $(".jnotify-message"),
    LOGIN_ERROR_LABEL = $(".error"),
    USER_RIGHTS_LABEL = $("#rights");

    //SEARCH FIELDS
    public static final SelenideElement
    SEARCH_LOGIN_INPUT_FIELD = $(By.xpath(".//input[@name='search_login']")),
    SEARCH_LASTNAME_INPUT_FIELD = $(By.xpath(".//input[@name='search_lastname']")),
    SEARCH_FIRSTNAME_INPUT_FIELD = $(By.xpath(".//input[@name='search_firstname']")),
    SEARCH_PHONE_INPUT_FIELD = $(By.xpath(".//input[@name='search_phonemobile']")),
    SEARCH_MAIL_INPUT_FIELD = $(By.xpath(".//input[@name='search_email']")),
    SEARCH_BUTTON = $(By.xpath(".//button[@name='button_search_x']")),

    DELETE_USER_BTN = $(withText("Удалить")),
    TURN_OFF_USER_BTN = $(withText("Выключать")),
    TURN_ON_USER_BTN = $(withText("Возобновить")),
    TURN_ACCEPT_BTN = $(withText("Да")),
    USER_RIGHTS_BTN = $("#rights");

    ElementsCollection RIGHTS =  $$(By.xpath(".//td[@class='center']/a"));

    //TABLE
    private static int table_iterator = 3;
    private static SelenideElement TABLE_ROW = $(By.xpath(".//table//tr[" + table_iterator + "]")),
    TABLE_ROW_LOGIN = $(By.xpath(".//table//tr[" + table_iterator + "]//span[@class='nopadding usertext']")),
    TABLE_ROW_SURNAME = $(By.xpath(".//table//tr[" + table_iterator + "]//td[@class='tdoverflowmax150'][1]")),
    TABLE_ROW_NAME = $(By.xpath(".//table//tr[" + table_iterator + "]//td[@class='tdoverflowmax150'][2]")),
    TABLE_ROW_PHONE = $(By.xpath(".//table//tr[" + table_iterator + "]//td[@class='tdoverflowmax125'][3]")),
            START_TABLE_HREF = $(By.xpath(".//table//tr["+table_iterator+"]//a"));

    private static final ElementsCollection TABLE_ELEMENTS = $$(By.xpath(".//span[@class='nopadding usertext']"));
    private static ElementsCollection TABLE_HREFS = $$(By.xpath(".//td[@class='nowraponall tdoverflowmax150']/a"));

    private final int watchUsersData = 1,
            createUser = 2,
            deleteUser = 4,
            changeOwnPassword = 6;

    public boolean allowWatchUserData(String login){
        if (clickByUser(login)) {
            USER_RIGHTS_BTN.click();
            RIGHTS.get(watchUsersData).click();
            return true;
        }
        return false;
    }

    public boolean allowCreateUser(String login){
        if (clickByUser(login)) {
            USER_RIGHTS_BTN.click();
            RIGHTS.get(createUser).click();
            return true;
        }
        return false;
    }

    public boolean allowDeleteUser(String login){
        if (clickByUser(login)) {
            USER_RIGHTS_BTN.click();
            RIGHTS.get(deleteUser).click();
            return true;
        }
        return false;
    }

    public boolean allowChangeOwnPassword(String login){
        if (clickByUser(login)){
        USER_RIGHTS_BTN.click();
        RIGHTS.get(changeOwnPassword).click();
        return true;
        }
        return false;
    }

    public boolean clickByUser(String login){
        int href_index = 0;
        for(SelenideElement el: TABLE_ELEMENTS){
            if(el.text().equals(login)){
                TABLE_HREFS.get(href_index).click();
                return true;
            }
            href_index ++;
        }
        return false;
    }

    public boolean deleteUser(String login){
        if (clickByUser(login)){
            DELETE_USER_BTN.click();
            return true;
        }
        return false;

    }

    public boolean turnOffUser(String login){
        if (clickByUser(login)){
            TURN_OFF_USER_BTN.click();
            TURN_ACCEPT_BTN.click();
            return true;
        }
        return false;
    }

    public boolean turnOnUser(String login){
        if (clickByUser(login)){
            TURN_ON_USER_BTN.click();
            TURN_ACCEPT_BTN.click();
            return true;
        }
        return false;
    }


    public boolean assertionSearchByLogin(String login){
        TABLE_ROW.shouldBe(Condition.exist);
        return TABLE_ROW_LOGIN.text().equals(login);
    }
    public boolean assertionSearchByName(String name){
        TABLE_ROW.shouldBe(Condition.exist);
        return TABLE_ROW_NAME.text().equals(name);
    }
    public boolean assertionSearchBySurname(String surname){
        TABLE_ROW.shouldBe(Condition.exist);
        return TABLE_ROW_SURNAME.text().equals(surname);
    }
    public boolean assertionSearchByPhone(String phone){
        TABLE_ROW.shouldBe(Condition.exist);
        return TABLE_ROW_PHONE.text().equals(phone);
    }

    public UsersAndGroupsPage(String href) {
        super(href);
    }

    public UsersAndGroupsPage setSearchLastnameField(String value) throws PageTypeException {
        SEARCH_LASTNAME_INPUT_FIELD.val(value);
        getSearchResults();
        return ApplicationRoute.getUsersAndGroupsPage();
    }
    public UsersAndGroupsPage setSearchFirstnameField(String value) throws PageTypeException {
        SEARCH_FIRSTNAME_INPUT_FIELD.val(value);
        getSearchResults();
        return ApplicationRoute.getUsersAndGroupsPage();
    }
    public UsersAndGroupsPage setSearchPhoneField(String value) throws PageTypeException {
        SEARCH_PHONE_INPUT_FIELD.val(value);
        getSearchResults();
        return ApplicationRoute.getUsersAndGroupsPage();
    }
    public UsersAndGroupsPage setSearchMailField(String value) throws PageTypeException {
        SEARCH_MAIL_INPUT_FIELD.val(value);
        getSearchResults();
        return ApplicationRoute.getUsersAndGroupsPage();
    }
    public UsersAndGroupsPage setSearchLoginField(String value) throws PageTypeException {
        SEARCH_LOGIN_INPUT_FIELD.val(value);
        getSearchResults();
        return ApplicationRoute.getUsersAndGroupsPage();
    }

    private UsersAndGroupsPage getSearchResults() throws PageTypeException {
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
    public UsersAndGroupsPage openUserListPage() throws PageTypeException {
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

    public void createUserErrorLabelExist(){
        CREATE_USER_ERROR_LABEL.shouldBe(Condition.visible);
    }
    public void loginUserErrorLabelExist(){
        LOGIN_ERROR_LABEL.shouldBe(Condition.visible);
    }
}
