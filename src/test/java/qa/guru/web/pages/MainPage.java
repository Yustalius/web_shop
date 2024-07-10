package qa.guru.web.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import org.junit.jupiter.api.Disabled;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.*;
import static java.lang.String.format;
import static org.assertj.core.api.Assertions.assertThat;

public class MainPage {
    private static final SelenideElement
            loginButton = $(".ico-login"),
            accountLabel = $(".account"),
            cartButton = $(".ico-cart"),
            cartQuantity = $("span.cart-qty");
    private static final ElementsCollection
            products = $$("h2.product-title");

    @Step("Открыть главную страницу")
    public static MainPage openMainPage() {
        open("/");

        return new MainPage();
    }

    public MainPage clickOnLoginButton() {
        loginButton.click();

        return this;
    }

    public MainPage verifyUserEmailIsShowed(String userEmail) {
        accountLabel.shouldHave(text(userEmail));

        return this;
    }

    public MainPage clickOnProduct() {
        products.get(1).click();


        return this;
    }

    public MainPage clickOnProductWithNumber(int productNumber) {
        products.get(productNumber-1).click();


        return this;
    }

    @Disabled
    public int getProductIdWithNumber(int productNumber) {
        return Integer.parseInt(products.get(productNumber-1).getAttribute("data-productid")); // todo change css selector
    }

    public String getProductName(int productId) {
        return $(format("[data-productid='%s'] div.details h2 a", productId)).getText();
    }

    public static int getNumberOfProductsInCartLabel() {

        return Integer.parseInt(
                cartQuantity.getText().replaceAll("\\D+", ""));
    }

    public static MainPage clickOnCartButton() {
        cartButton.click();

        return null;
    }

    public static MainPage checkCartQuantityChanged(int numberOfProductsInCartBeforeAdding, int productsAdded) {
        assertThat(getNumberOfProductsInCartLabel())
                .isEqualTo(numberOfProductsInCartBeforeAdding + productsAdded);
        return null;
    }
}
