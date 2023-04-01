package pages.defaultPages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import pages.Page;

import static com.codeborne.selenide.Selenide.$;

public class ProductPage extends Page {
    private static final SelenideElement HREF_INPUT_FIELD = $("#ref"),
    NAME_INPUT_FIELD = $(By.xpath(".//input[@name='label']")),
    DESCRIPRION_INPUT_FIELD = $("#desc"),
    WEIGHT_INPUT_FIELD = $(By.xpath(".//input[@name='weight']")),
            PRICE_INPUT_FIELD = $(By.xpath(".//input[@name='price']"));


    public ProductPage(String href) {
        super(href);
    }
}
