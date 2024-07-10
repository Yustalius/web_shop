package qa.guru.web.pages;

import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import qa.guru.utils.NoSuchOptionException;

import static com.codeborne.selenide.CollectionCondition.size;
import static com.codeborne.selenide.Selectors.byTagAndText;
import static com.codeborne.selenide.Selenide.*;

public class ProductPage {
    private final SelenideElement fourProductsPerPageOption = $(byTagAndText("option", "4")),
            productPageSize = $("#products-pagesize");
    private final ElementsCollection productBoxes = $$("div.item-box");

    public static String apparelAndShoes = "/apparel-shoes";

    @Step("Открыть страницу с продуктами")
    public static ProductPage openProductPage(String page) {
        open(page);

        return new ProductPage();
    }

    public ProductPage selectProductsPerPageOption(int productsPerPage) throws NoSuchOptionException {
        if (productsPerPage == 4 || productsPerPage == 8 || productsPerPage == 12) {
            productPageSize.selectOption(Integer.toString(productsPerPage));
        } else throw new NoSuchOptionException();

        return this;
    }

    public ProductPage verifyAmountOfObjectsShowedOnPage(int productsPerPage) {
        productBoxes.shouldHave(size(productsPerPage));

        return this;
    }
}
