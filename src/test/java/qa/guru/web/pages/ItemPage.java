package qa.guru.web.pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ItemPage {

    private static final SelenideElement
            productName = $("[itemprop='name']"),
            addToCartButton = $(".add-to-cart-button"),
            notificationBar = $("#bar-notification");

    public String getProductName() {
        return productName.getText();
    }

    public ItemPage clickOnAddToCartButton() {
        addToCartButton.click();

        return this;
    }
    public static ItemPage checkNotificationBarIsVisible() {
        notificationBar.shouldBe(visible);

        return null;
    }

}
