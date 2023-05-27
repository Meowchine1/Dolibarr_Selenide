package pages.defaultPages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.selector.WithText;
import exceptions.PageTypeException;
import models.Company;
import org.openqa.selenium.By;
import pages.Page;
import webApplication.ApplicationRoute;

import java.io.File;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class CompanyPage extends Page {
    private static String SUCCESS_MESSAGE = "Настройки сохранены";
    private static final SelenideElement
            NAME_INPUT_FIELD = $(By.id("name")),
            ADRESS_INPUT_FIELD = $(By.id("MAIN_INFO_SOCIETE_ADDRESS")),
            ZIP_CODE_INPUT_FIELD = $(By.id("MAIN_INFO_SOCIETE_ADDRESS")),
            CITY_INPUT_FIELD = $(By.id("MAIN_INFO_SOCIETE_TOWN")),
            PHONE_INPUT_FIELD = $(By.id("phone")),
            FAX_INPUT_FIELD = $(By.id("fax")),
            EMAIL_INPUT_FIELD = $(By.id("email")),
            LOGOTYPE_INPUT_FILE = $(By.id("logo")),
            SAVE_BTN = $(By.xpath(".//input[@name='save']")),
            SUCCESS_MESSAGE_LABEL = $(withText(SUCCESS_MESSAGE));
    public CompanyPage(String href) {
        super(href);
    }

    public CompanyPage fillCompanyInf(Company company) throws PageTypeException {
        NAME_INPUT_FIELD.setValue(company.getName());
        ADRESS_INPUT_FIELD.setValue(company.getAddress());
        ZIP_CODE_INPUT_FIELD.setValue(company.getZipCode());
        CITY_INPUT_FIELD.setValue(company.getCity());
        PHONE_INPUT_FIELD.setValue(company.getPhone());
        FAX_INPUT_FIELD.setValue(company.getFax());
        EMAIL_INPUT_FIELD.setValue(company.getEmail());

        SAVE_BTN.click();

        return ApplicationRoute.getCompanyPage();
    }

    public void setLogo(String path){
        File file = new File(path);
        LOGOTYPE_INPUT_FILE.sendKeys(file.getAbsolutePath());
        SAVE_BTN.click();
    }

    public void succesMessageShouldBe(){
        SUCCESS_MESSAGE_LABEL.shouldBe(Condition.exist);
    }

    public void succesMessageShouldNotBe(){
        SUCCESS_MESSAGE_LABEL.shouldNotBe(Condition.exist);
    }

}
