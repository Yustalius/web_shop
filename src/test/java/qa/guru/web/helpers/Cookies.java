package qa.guru.web.helpers;

import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.WebDriverRunner.getWebDriver;

public class Cookies {

    public void addCookies(String cookieName, String cookie) {
        getWebDriver().manage().addCookie(new Cookie(cookieName, cookie));
    }
}
