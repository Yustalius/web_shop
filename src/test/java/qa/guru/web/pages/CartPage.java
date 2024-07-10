package qa.guru.web.pages;

import com.codeborne.selenide.CollectionCondition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.ex.ElementNotFound;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static org.assertj.core.api.Assertions.assertThat;

public class CartPage {
    private static final String CART_URL = "/cart";
    private static final SelenideElement updateCartButton = $("[name='updatecart']"),
            orderSummaryLabel = $(".order-summary-content");
    private static final ElementsCollection removeProductCheckboxes = $$("[name='removefromcart']"),
            productNameTitle = $$(".product-name");

    @Step("Открыть страницу корзины")
    public static CartPage openCartPage() {
        open(CART_URL);

        return new CartPage();
    }

    private int findElementQuantityInCartByName(String productName) {
        return Integer.parseInt(
                $$(byTagAndText("a", productName))
                        .get(1).parent().sibling(1).lastChild().getValue());
    }

    private static void clickOnRemoveCheckboxes() {
        for (int i = 0; i <= removeProductCheckboxes.size() - 1; i++) {
            removeProductCheckboxes.get(i).click();
        }
    }

    public CartPage checkProductName(String productName) {
        productNameTitle.findBy(text(productName)).shouldHave(text(productName));


        return this;
    }

    public CartPage checkProductQuantity(String productName, int numberOfProducts) {
        assertThat(findElementQuantityInCartByName(productName)).isEqualTo(numberOfProducts);

        return this;
    }

    @Step("Очистить корзину")
    public static CartPage clearCart() {
        openCartPage();
        try {
            step("Нажать на чекбоксы около элементов в корзине", () -> clickOnRemoveCheckboxes());
            step("Нажать на кнопку 'Update shopping cart'", () -> updateCartButton.click());
        } catch (ElementNotFound e) {}

        return new CartPage();
    }

    @Step("Проверить, что корзина пустая")
    public CartPage verifyCartIsEmpty() {
        orderSummaryLabel.shouldHave(text("Your Shopping Cart is empty!"));

        return this;
    }

    public CartPage checkCartIsNotEmpty() {
        productNameTitle.shouldHave(CollectionCondition.sizeGreaterThan(0));

        return this;
    }
}
