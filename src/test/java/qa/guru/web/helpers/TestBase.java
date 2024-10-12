package qa.guru.web.helpers;

import com.codeborne.selenide.logevents.SelenideLogger;
import org.junit.jupiter.api.AfterEach;
import qa.guru.config.ProjectProvider;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

public class TestBase {


    @BeforeAll
    static void beforeAll() {
        ProjectProvider projectProvider = new ProjectProvider();
    }

    @BeforeEach
    void addListener() {
        SelenideLogger.addListener("AllureSelenide", new AllureSelenide());
    }

    @AfterEach
    void addAttachments() {
        AttachManager.takeScreenshot("Last Screenshot");
        AttachManager.htmlPageSource();
        AttachManager.pageSource();
        AttachManager.browserConsoleLogs();
        AttachManager.addVideo();
    }
}
