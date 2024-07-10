package qa.guru.web.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import qa.guru.web.data.TestData;
import qa.guru.web.helpers.TestBase;
import qa.guru.web.pages.LoginPage;
import qa.guru.web.pages.MainPage;

import static io.qameta.allure.Allure.step;
import static qa.guru.web.helpers.Login.logOut;
import static qa.guru.web.pages.LoginPage.openLoginPage;
import static qa.guru.web.pages.MainPage.openMainPage;

@Story("Тесты на авторизацию")
public class LoginTests extends TestBase {
    public static TestData config = ConfigFactory.create(TestData.class, System.getProperties());

    MainPage mainPage = new MainPage();
    LoginPage loginPage = new LoginPage();

    public String userEmail = config.getUserEmail(),
            userPassword = config.getUserPassword();

    @Test
    @Tags({@Tag("UI"), @Tag("Positive")})
    @DisplayName("Авторизация через интерфейс")
    @Severity(SeverityLevel.BLOCKER)
    public void loginWithUItest() {
        step("Открыть главную страницу", () ->
                openMainPage());
        step("Нажать на кнопку 'Log In' в хэдэре", () ->
                mainPage.clickOnLoginButton());
        step("Проверить, что открыта страница с Логином", () ->
                loginPage.verifyLoginPageIsOpened());
        step("Ввести логин и пароль", () -> {
            loginPage.setUserEmail(userEmail)
                    .setUserPassword(userPassword);
        });
        step("Нажать на кнопку 'Log In'", () ->
                loginPage.clickOnLoginButton());
        step("Проверить, что Email пользователя отображается на главной странице", () ->
                mainPage.verifyUserEmailIsShowed(userEmail));
        logOut();
    }

    @Tags({@Tag("UI"), @Tag("Negative")})
    @CsvFileSource(resources = "/negativeloginparams.csv")
    @ParameterizedTest(name = "Негативные тесты на авторизацию email = {0}")
    public void nonValidEmailLogInTest(
            String userEmail
    ) {
        openLoginPage();
        step("Ввести email и нажать 'Log in'", () ->
                loginPage.setUserEmail(userEmail)
                        .clickOnLoginButton());
        step("Проверить, что появилось сообщение об ошибке 'Please enter a valid email address.'", () ->
                loginPage.verifyEmailValidationErrorAppear());
    }
}
