package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import lombok.AllArgsConstructor;
import org.openqa.selenium.logging.LogEntries;

@AllArgsConstructor
public class Page {
    private String href;

    public void open(){
        Selenide.open(href);

    }
}
