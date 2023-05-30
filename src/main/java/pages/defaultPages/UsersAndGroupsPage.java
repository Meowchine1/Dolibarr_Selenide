package pages.defaultPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import config.Hrefs;
import exceptions.PageTypeException;
import models.InnerUser;
import org.openqa.selenium.By;
import pages.Page;
import webApplication.ApplicationRoute;

import static com.codeborne.selenide.Condition.attribute;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class UsersAndGroupsPage extends Page  {
    private static final ElementsCollection SEARCH_RESULT = $$(By.xpath(".//tr[@class='oddeven']"));
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
    NO_RIGHTS = $(withText("Доступ запрещен.")),
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

    private final int watchUsersData = 0,
            createUser = 1,
            deleteUser = 3,
            changeOwnPassword = 5;

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
        String substringForSearch = login.substring(0, 10);
        for(SelenideElement el: TABLE_ELEMENTS){
            if(el.text().contains(substringForSearch)){
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

//            executeJavaScript("document.getElementById('topmenuloginmoreinfo').type='display';");
//
//
//            TURN_ACCEPT_BTN.click();

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

public int searchResultCount(){
        return SEARCH_RESULT.size();
}

    public UsersAndGroupsPage(String href) {
        super(href);
    }

    public UsersAndGroupsPage setSearchLastnameField(String value) throws PageTypeException {
        SEARCH_LASTNAME_INPUT_FIELD.val(value);
        searchResults();
        return ApplicationRoute.getUsersAndGroupsPage();
    }
    public UsersAndGroupsPage setSearchFirstnameField(String value) throws PageTypeException {
        SEARCH_FIRSTNAME_INPUT_FIELD.val(value);
        searchResults();
        return ApplicationRoute.getUsersAndGroupsPage();
    }
    public UsersAndGroupsPage setSearchPhoneField(String value) throws PageTypeException {
        SEARCH_PHONE_INPUT_FIELD.val(value);
        searchResults();
        return ApplicationRoute.getUsersAndGroupsPage();
    }
    public UsersAndGroupsPage setSearchMailField(String value) throws PageTypeException {
        SEARCH_MAIL_INPUT_FIELD.val(value);
        searchResults();
        return ApplicationRoute.getUsersAndGroupsPage();
    }
    public UsersAndGroupsPage setSearchLoginField(String value) throws PageTypeException {
        SEARCH_LOGIN_INPUT_FIELD.val(value);
        searchResults();
        return ApplicationRoute.getUsersAndGroupsPage();
    }

    public UsersAndGroupsPage searchResults() throws PageTypeException {
        SEARCH_BUTTON.click();
        return ApplicationRoute.getUsersAndGroupsPage();
    }

    public int userListSize(){
        int size = 0;
        while(TABLE_ROW.is(Condition.exist)){
            size++;
            table_iterator +=1;
            START_TABLE_HREF = $(By.xpath(".//table//tr["+table_iterator+"]//a"));
            TABLE_ROW = $(By.xpath(".//table//tr[" + table_iterator + "]"));
        }
        table_iterator = 3;
        return size;
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

    public UsersAndGroupsPage createStubUser(InnerUser user) throws PageTypeException {
        Selenide.open(Hrefs.CREATE_USER_HREF);
        LASTNAME_INPUT_FIELD.setValue("FAKE");
        FIRSTNAME_INPUT_FIELD.setValue("fake");
        LOGIN_INPUT_FIELD.setValue(user.getLogin());
        PASSWORD_INPUT_FIELD.setValue(user.getPassword());
        SAVE_USER_BUTTON.click();
        return ApplicationRoute.getUsersAndGroupsPage();
    }

    public UsersAndGroupsPage createUser(InnerUser user) throws PageTypeException {
        Selenide.open(Hrefs.CREATE_USER_HREF);
        LASTNAME_INPUT_FIELD.setValue(user.getLastname());
        FIRSTNAME_INPUT_FIELD.setValue(user.getName());
        LOGIN_INPUT_FIELD.setValue(user.getLogin());
        setIsAdmin(user.getIsAdmine());
        setIsEmployee(user.getIsEmployee());
        PASSWORD_INPUT_FIELD.setValue(user.getPassword());
        ADDRESS_TEXTAREA.setValue(user.getAddress());
        ZIPCODE_INPUT_FIELD.setValue(user.getZipCode());
        CITY_INPUT_FIELD.setValue(user.getCity());
        PHONE_INPUT_FIELD.setValue(user.getPhone());
        FAX_INPUT_FIELD.setValue(user.getFax());
        EMAIL_INPUT_FIELD.setValue(user.getEmail());
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

    public UsersAndGroupsPage showUserList() throws PageTypeException {
        Selenide.open(Hrefs.USER_LIST_HREF);
        return ApplicationRoute.getUsersAndGroupsPage();
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
    public void cantAddNewUser(){
        $(By.xpath(".//a[@title='Новый пользователь']")).shouldNotHave(attribute("href"));
    }
    public void cantWatchUsers(){
        $(By.xpath(".//a[@title='Пользователи и Группы']")).shouldNotHave(attribute("href"));
    }
    public void canAddNewUser(){
        $(By.xpath(".//a[@title='Новый пользователь']")).shouldHave(attribute("href"));
    }
    public void canWatchUsers(){
        $(By.xpath(".//a[@title='Список пользователей']")).shouldHave(attribute("href"));
    }
}
