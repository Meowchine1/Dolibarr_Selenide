package pages.defaultPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.WithText;
import config.Hrefs;
import config.UserType;
import exceptions.PageTypeException;
import org.openqa.selenium.By;
import pages.Page;
import webApplication.ApplicationRoute;

import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withTagAndText;
import static com.codeborne.selenide.Selenide.$;

public class ThirdPartiesPage extends Page {

    private static int table_iterator = 3;
    private static SelenideElement START_TABLE_ROW = $(By.xpath(".//table//tr[" + table_iterator + "]")),
    START_TABLE_HREF = $(By.xpath(".//table//tr["+table_iterator+"]//a"));
    private static final SelenideElement
            FIRSTNAME_INPUT_FIELD = $("#firstname"),
            ADDRESS_TEXTAREA = $("address"),
            ZIPCODE_INPUT_FIELD = $("#zipcode"),
            CITY_INPUT_FIELD = $("#town"),
            PHONE_INPUT_FIELD = $("#phone_mobile"),
            EMAIL_INPUT_FIELD = $("#email"),
            SAVE_USER_BUTTON = $(By.xpath("//input[@name='add']")),
            ERROR_LABEL = $(".jnotify-message"),
            LOGIN_INPUT_FIELD = $("#login"),
            PASSWORD_INPUT_FIELD = $("#password"),
            CREATE_ACCOUNT_BUTTON = $(".aaa"),
            CHECK_ACCOUNT_BUTTON = $(By.xpath(".//input[@value='Проверить']")),
            REGISTER_LABEL = $(withTagAndText("td","Созданный пользователь будет")),
            SELECT_OPTION_USER_TYPE = $("#confirm");

    // SEARCH FIELDS
    private static final SelenideElement
            SEARCH_NAME_INPUT_FIELD = $(By.xpath(".//input[@name='search_nom']")),
            SEARCH_ZIP_INPUT_FIELD = $(By.xpath(".//input[@name='search_zip']")),
            SEARCH_PHONE_INPUT_FIELD = $(By.xpath(".//input[@name='search_phone']")),
            SEARCH_BUTTON = $(By.xpath(".//button[@name='button_search_x']"));

    public ThirdPartiesPage(String href) {
        super(href);
    }

    public void getListInf(){

        while(START_TABLE_ROW.is(Condition.exist)){
            table_iterator +=1;
            START_TABLE_ROW = $(By.xpath(".//table//tr[" + table_iterator + "]"));
        }
        table_iterator = 3;
    }

    public void thirdPartiesListShouldnotBeVisible(){
        START_TABLE_ROW.shouldNotBe(Condition.visible);
    }


    public ThirdPartiesPage createThirdParties(String name, String address, String zipcode, String phone,
                                               String email, String city) throws PageTypeException {
        Selenide.open(Hrefs.CREATE_THIRD_PARTIES_HREF);
        FIRSTNAME_INPUT_FIELD.setValue(name);
        ADDRESS_TEXTAREA.setValue(address);
        ZIPCODE_INPUT_FIELD.setValue(zipcode);
        CITY_INPUT_FIELD.setValue(city);
        PHONE_INPUT_FIELD.setValue(phone);
        EMAIL_INPUT_FIELD.setValue(email);
        SAVE_USER_BUTTON.click();
        return ApplicationRoute.getThirdPartiesPage();
    }

    public ThirdPartiesPage createContact(String name, String address, String zipcode, String phone,
                                               String email, String city) throws PageTypeException {

        Selenide.open(Hrefs.CREATE_CONTACT_HREF);
        FIRSTNAME_INPUT_FIELD.setValue(name);
        ADDRESS_TEXTAREA.setValue(address);
        ZIPCODE_INPUT_FIELD.setValue(zipcode);
        CITY_INPUT_FIELD.setValue(city);
        PHONE_INPUT_FIELD.setValue(phone);
        EMAIL_INPUT_FIELD.setValue(email);
        SAVE_USER_BUTTON.click();
        return ApplicationRoute.getThirdPartiesPage();
    }

    public Map<String, String> registerContact(UserType userType ) throws PageTypeException {
        // зайти в список контактов
        Selenide.open(Hrefs.CONATC_LIST_HREF);
        // выбрать контакт (пусть рандомный) нажать на него
        START_TABLE_HREF.click();
        // нажать кнопку создать аккаунт долибар
        CREATE_ACCOUNT_BUTTON.shouldBe(Condition.visible).click();

        //  запомнить сгенерированные данные
        String login = LOGIN_INPUT_FIELD.getValue(), password = PASSWORD_INPUT_FIELD.getValue();

        // выбор типа полоьзователя внешний\невнешний
        if(userType.equals(UserType.EXTERNAL)){
            if(REGISTER_LABEL.shouldBe(Condition.visible).text().contains("внешний")){
                    // select option value = да
                SELECT_OPTION_USER_TYPE.selectOptionByValue("yes");
            }
            else{
                // select-option = нет
                SELECT_OPTION_USER_TYPE.selectOptionByValue("no");
            }
        }
        else{
            if(REGISTER_LABEL.shouldBe(Condition.visible).text().contains("внешний")){
                // select option value = нет
                SELECT_OPTION_USER_TYPE.selectOptionByValue("no");
            }
            else {
                // select option value = да
                SELECT_OPTION_USER_TYPE.selectOptionByValue("yes");
            }
        }
        // нажать кнопку "проверить"
        CHECK_ACCOUNT_BUTTON.click();
        //сохранить данные для входа в аккаунт
        Map<String, String> data = new HashMap<>();
        data.put("login", login);
        data.put("password", password);
        return  data;
    }

    public ThirdPartiesPage openThirdPartiesList() throws PageTypeException {

        Selenide.open(Hrefs.THIRD_PARTIES_LIST_HREF);
        return ApplicationRoute.getThirdPartiesPage();
    }

    public ThirdPartiesPage openContactList() throws PageTypeException {

        Selenide.open(Hrefs.CONATC_LIST_HREF);
        return ApplicationRoute.getThirdPartiesPage();
    }

    public void showContsctsList(){
        Selenide.open(Hrefs.CONATC_LIST_HREF);
    }
    public void showThirdPartiesList(){
        Selenide.open(Hrefs.THIRD_PARTIES_LIST_HREF);
    }
    public void setNameSearchField(String value){
        SEARCH_NAME_INPUT_FIELD.val(value);
    }

    public void setZipCodeSearchField(String value){
        SEARCH_ZIP_INPUT_FIELD.val(value);
    }

    public void setPhoneSearchField(String value){
        SEARCH_PHONE_INPUT_FIELD.val(value);
    }

    public void getSearchResults(){
        SEARCH_BUTTON.click();
    }
}
