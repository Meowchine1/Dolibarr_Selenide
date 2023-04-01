package pages.defaultPages.usersAndGroups;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class CreateUserPage {
    private static final SelenideElement
            ROLE_DROPDOWN = $(By.id("")),
            NAME_INPUT_FIELD = $(By.id("firstname")),
            LOGIN_INPUT_FIELD = $(By.id("")),
            LASTNAME_INPUT_FIELD = $(By.id("lastname")),
            EMAIL_INPUT_FIELD = $(By.xpath(".//input[@name='email']")),
            IS_ADMINE_CHECKBOX = $(By.id("")),
            SEX_DROPDOWN = $(By.id("")),
            IS_EMPLOYEE_CHECKBOX = $(By.id("")),
            SUPERVISOR_DROPDOWN = $(By.id("select2-fk_user-container")),
            HOLIDAY_SUPERVISOR_DROPDOWN = $(By.id("select2-fk_user_holiday_validator-container")),
            PASSWORD_INPUT_FIELD = $(By.xpath(".//input[@name='password']")),
            ADRESS_INPUT_FIELD = $(By.id("address")),
            ZIPCODE_INDEX_INPUT_FIELD = $(By.id("zipcode")),
            CITY_INPUT_FIELD = $(By.id("town")),
            COUNTRY_DROPDOWN = $(By.id("select2-selectcountry_id-container")),
            PHONE_INPUT_FIELD = $(By.xpath(".//input[@name='user_mobile']")),
            FAX_INPUT_FIELD = $(By.id("")),
            JOB_TITLE_INPUT_FIELD = $(By.xpath(".//input[@name='job']")),
            BIRTHDAY = $(By.id("dateofbirth"));

}
