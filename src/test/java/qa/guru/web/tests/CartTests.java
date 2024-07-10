package qa.guru.web.tests;

import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import qa.guru.web.data.TestData;
import qa.guru.web.helpers.TestBase;
import qa.guru.web.pages.CartPage;
import qa.guru.web.pages.ItemPage;
import qa.guru.web.pages.MainPage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static java.lang.String.format;
import static qa.guru.web.data.Users.standart_user;
import static qa.guru.web.helpers.APIRequests.addToCartAPI;
import static qa.guru.web.helpers.Login.logOut;
import static qa.guru.web.helpers.Login.loginWithAPI;
import static qa.guru.web.helpers.Random.getRandomNumber;
import static qa.guru.web.pages.CartPage.clearCart;
import static qa.guru.web.pages.CartPage.openCartPage;
import static qa.guru.web.pages.ItemPage.checkNotificationBarIsVisible;
import static qa.guru.web.pages.MainPage.*;

@Story("Тесты на добавление продукта в корзину")
public class CartTests extends TestBase {
    MainPage mainPage = new MainPage();
    ItemPage itemPage = new ItemPage();
    CartPage cartPage = new CartPage();

    public static TestData config = ConfigFactory.create(TestData.class, System.getProperties());

    String productName = config.getProductName();

    @BeforeEach
    public void before() {
        loginWithAPI(standart_user);
        clearCart();
    }

    @AfterEach
    public void after() {
        step("Очистить корзину и проверить, что она пустая", () ->
                clearCart().verifyCartIsEmpty());
        logOut();
    }

    @Test
    @Tags({@Tag("UI"), @Tag("Positive"), @Tag("CART"), @Tag("jopa")})
    @DisplayName("Добавление продукта в корзину")
    @Severity(SeverityLevel.BLOCKER)
    public void addToCartTest() {
        step("Открыть главную страницу", () -> openMainPage());
        step("Нажать на продукт на главной странице", () -> mainPage.clickOnProduct());

        int numberOfProductsInCartBeforeAdding = getNumberOfProductsInCartLabel();

        step("Нажать на кнопку 'Add to cart'", () -> itemPage.clickOnAddToCartButton());
        sleep(500);
        step("Проверить появление полоски уведомления", () -> checkNotificationBarIsVisible());
        step("Проверить, что в тексте 'Shopping cart(кол-во товаров)' количество изменилось на +1", () ->
                checkCartQuantityChanged(numberOfProductsInCartBeforeAdding, 1));
        step("Нажать на кнопку 'Shopping cart'", () -> clickOnCartButton());

        step("Проверить, что название продукта соответствует названию с его страницы, и количество продуктов в корзине", () ->
                cartPage.checkProductName(productName)
                .checkProductQuantity(productName, numberOfProductsInCartBeforeAdding + 1));
    }

    @Test
    @Tags({@Tag("UI"), @Tag("API"), @Tag("Positive"), @Tag("CART")})
    @DisplayName("Добавление продуктов в корзину с помощью API")
    @Severity(SeverityLevel.BLOCKER)
    public void addToCartWithApiTest() {
        int numberOfProductsInCartBeforeAdding = getNumberOfProductsInCartLabel();
        int numberOfProductsAdded = getRandomNumber(1, 10);

        openMainPage();
        step(format("Добавить в корзину %s ед. товара '%s'", numberOfProductsAdded, productName), () ->
                addToCartAPI(standart_user, config.getProductId(), numberOfProductsAdded));
        step("Обновить страницу", () -> refresh());

        step(format("Проверить, что в тексте 'Shopping cart(кол-во товаров)' количество изменилось на +%s", numberOfProductsAdded), () ->
                checkCartQuantityChanged(numberOfProductsInCartBeforeAdding, numberOfProductsAdded));
        step("Нажать на кнопку 'Shopping cart'", () -> clickOnCartButton());
        step("Проверить, что название продукта соответствует названию с его страницы, и количество продуктов в корзине", () ->
                cartPage.checkProductName(productName)
                        .checkProductQuantity(productName, numberOfProductsInCartBeforeAdding + numberOfProductsAdded));
    }

    @Test
    @Tags({@Tag("UI"), @Tag("Positive"), @Tag("CART")})
    @DisplayName("Проверка очистки корзины")
    @Severity(SeverityLevel.BLOCKER)
    public void clearCartTest() {
        step("Добавить в корзину несколько товаров", () -> {
                addToCartAPI(standart_user, config.getProductId());
                addToCartAPI(standart_user, config.getSecondProductId());
                addToCartAPI(standart_user, config.getThirdProductId());});
        openCartPage();
        step("Проверить наличие продуктов в корзине", () -> cartPage.checkCartIsNotEmpty());
        clearCart().verifyCartIsEmpty();
    }
}










