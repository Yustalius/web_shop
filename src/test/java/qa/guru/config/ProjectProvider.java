package qa.guru.config;

import com.codeborne.selenide.Configuration;
import org.aeonbits.owner.ConfigFactory;
import org.openqa.selenium.remote.DesiredCapabilities;

public class ProjectProvider {

    private final PROP.PropInterface config;


    public ProjectProvider() {
        this.config = ConfigFactory.create(PROP.PropInterface.class, System.getProperties());
        testConfiguration();
    }

    public void testConfiguration() {
        switch (config.getBrowser().toLowerCase()) {
            case "firefox":
                Configuration.browser = "firefox";
                break;
            case "chrome":
                Configuration.browser = "chrome";
                break;
            default:
                throw new RuntimeException("Browser was not chosen");
        }

        switch (config.getRemoteOrLocal()) {
            case "remote":
                Configuration.remote = config.getRemoteUrl();

                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("enableVNC", true);
                capabilities.setCapability("enableVideo", true);
                Configuration.browserCapabilities = capabilities;

                break;
            case "local":
                break;
            default:
                break;
        }

        Configuration.baseUrl = config.getBaseUrl();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 10000;
    }
}
