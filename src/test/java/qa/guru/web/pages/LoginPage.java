package qa.guru.web.pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.currentFrameUrl;
import static org.assertj.core.api.Assertions.assertThat;

public class LoginPage {
    private final String LOGIN_PAGE_URL = "https://demowebshop.tricentis.com/login";
    private final SelenideElement
            loginPageTitle = $(".page-title"),
            emailInput = $(".email"),
            passwordInput = $("#Password"),
            loginButton = $(".login-button"),
            emailValidationError = $("span[for=Email]");

    @Step("Открыть страницу авторизации")
    public static void openLoginPage() {
        open("/login");
    }

    public LoginPage verifyLoginPageIsOpened() {
        assertThat(currentFrameUrl()).isEqualTo(LOGIN_PAGE_URL);
        loginPageTitle.shouldHave(text("Welcome, Please Sign In!"));

        return this;
    }

    public LoginPage setUserEmail(String userEmail) {
        emailInput.setValue(userEmail);

        return this;
    }

    public LoginPage setUserPassword(String userPassword) {
        passwordInput.setValue(userPassword);

        return this;
    }

    public LoginPage clickOnLoginButton() {
        loginButton.click();

        return this;
    }

    public LoginPage verifyEmailValidationErrorAppear() {
        emailValidationError.shouldBe(visible);

        return this;
    }
}
