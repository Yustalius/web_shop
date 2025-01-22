package qa.guru;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.*;

public class TestTest {
    JopaPage page = new JopaPage();

    @Test
    void jopa() {
        Configuration.pageLoadTimeout = 100000;
        Configuration.holdBrowserOpen = true;
        open("https://demoqa.com/automation-practice-form");
        page.setName("anton gondon");
        page.setLastName("ballon");
        page.clickOnReadingCheckbox();
    }
}
