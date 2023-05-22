package pages.defaultPages;

import com.codeborne.selenide.SelenideElement;
import exceptions.PageTypeException;
import models.Company;
import org.openqa.selenium.By;
import pages.Page;
import webApplication.ApplicationRoute;

import java.io.File;

import static com.codeborne.selenide.Selenide.$;

public class CompanyPage extends Page {
    private static final SelenideElement
            NAME_INPUT_FIELD = $(By.id("name")),
            ADRESS_INPUT_FIELD = $(By.id("MAIN_INFO_SOCIETE_ADDRESS")),
            ZIP_CODE_INPUT_FIELD = $(By.id("MAIN_INFO_SOCIETE_ADDRESS")),
            CITY_INPUT_FIELD = $(By.id("MAIN_INFO_SOCIETE_TOWN")),
            PHONE_INPUT_FIELD = $(By.id("phone")),
            FAX_INPUT_FIELD = $(By.id("fax")),
            EMAIL_INPUT_FIELD = $(By.id("email")),
            LOGOTYPE_INPUT_FILE = $(By.id("logo")),
            SAVE_BTN = $(By.xpath(".//input[@name='save']"));

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

        File file = new File(company.getLogoPath());
        LOGOTYPE_INPUT_FILE.sendKeys(file.getAbsolutePath());

        SAVE_BTN.click();
        return ApplicationRoute.getCompanyPage();
    }

}
