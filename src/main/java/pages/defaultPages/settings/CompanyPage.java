package pages.defaultPages.settings;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.Page;

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
            WEBSITE_INPUT_FIELD = $(By.id("web")),
            OGRN_INPUT_FIELD = $(By.id("profid1")),
            INN_INPUT_FIELD = $(By.id("profid2")),
            KPP_INPUT_FIELD = $(By.id("profid3")),
            OKPO_INPUT_FIELD = $(By.id("profid4")),
            PAYERS_CODE_INPUT_FIELD = $(By.id("intra_vat")),
            COMPANY_OBJ_INPUT_FIELD = $(By.id("object")),
            NOTE_INPUT_FIELD = $(By.id("note")),
            DIRECTORS_NAMES_INPUT_FIELD = $(By.id("directors")),
            INFODIRECTORS_NAMES_INPUT_FIELD = $(By.id("infodirector")),
            CAPITAL_INPUT_FIELD = $(By.id("capital")),
            COUNTRY_SELECT_OPTION = $(By.id("select2-selectcountry_id-container")),
            STATE_SELECT_OPTION = $(By.id("select2-state_id-container")),
            CURRENCY_SELECT_OPTION = $(By.id("select2-currency-container")),
            FISCAL_MONTH_START_SELECT_OPTION = $(By.id("SOCIETE_FISCAL_MONTH_START")),
            BUISNESS_TYPE_SELECT_OPTION = $(By.id("select2-forme_juridique_code-container")),  // Пользовательски настраивается
            LOGOTYPE_FILE = $(By.id("logo")),
            SQUARRED_LOGOTYPE_FILE = $(By.id("logo_squarred")),
            SAVE_BTN = $(By.xpath(".//input[@name='save']"));

    public CompanyPage(String href) {
        super(href);
    }
}
