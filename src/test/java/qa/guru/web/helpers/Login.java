package qa.guru.web.helpers;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import qa.guru.web.data.TestData;
import org.aeonbits.owner.ConfigFactory;
import qa.guru.web.data.User;
import qa.guru.web.pages.MainPage;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static qa.guru.web.helpers.APIRequests.authAPI;
import static qa.guru.web.pages.MainPage.openMainPage;

public class Login extends TestBase {
    private final static SelenideElement logOutButton = $(".ico-logout");

    static MainPage mainPage = new MainPage();
    static Cookies cookies = new Cookies();

    static TestData config = ConfigFactory.create(TestData.class, System.getProperties());

    public static String minimumContentPage = config.getMinimumContentURL();

    @Step("Авторизоваться с помощью API")
    public static Login loginWithAPI(User user) {

        step("Добавить авторизационные Cookies", () -> {
            step("Открыть минимальный контент приложения", () -> open(minimumContentPage));
            step("Установить Cookies", () ->
                    cookies.addCookies("NOPCOMMERCE.AUTH", getAuthCookies(user)));
        });

        step("Открыть главную страницу", () ->
                openMainPage());

        step("Проверить авторизацию", () ->
                mainPage.verifyUserEmailIsShowed(user.getEmail()));

        return new Login();
    }

    public static String getAuthCookies(User user) {
        return step("Авторизоваться с API и достать Cookie", () ->
                authAPI(user));
    }

    @Step("Выйти из учетной записи")
    public static Login logOut() {
        logOutButton.click();

        return new Login();
    }
}
