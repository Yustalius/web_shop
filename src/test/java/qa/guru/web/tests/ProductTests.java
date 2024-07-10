package qa.guru.web.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import qa.guru.utils.NoSuchOptionException;
import qa.guru.web.helpers.TestBase;
import qa.guru.web.pages.ProductPage;

import static com.codeborne.selenide.Selenide.*;
import static io.qameta.allure.Allure.step;
import static qa.guru.web.pages.ProductPage.apparelAndShoes;
import static qa.guru.web.pages.ProductPage.openProductPage;

public class ProductTests extends TestBase {
    ProductPage productPage = new ProductPage();

    @Test
    @Tags({@Tag("Ui"), @Tag("Positive")})
    @DisplayName("Проверка отображения 4, 8 и 12 продуктов на странице")
    public void checkProductsPerPage() throws NoSuchOptionException {
        openProductPage(apparelAndShoes);

        step("Выбрать количество продуктов на странице 4 и проверить, что на странице 4 продукта", () ->
                productPage.selectProductsPerPageOption(4)
                .verifyAmountOfObjectsShowedOnPage(4));
        step("Выбрать количество продуктов на странице 8 и проверить, что на странице 8 продуктов", () ->
                productPage.selectProductsPerPageOption(8)
                .verifyAmountOfObjectsShowedOnPage(8));
        step("Выбрать количество продуктов на странице 12 и проверить, что на странице 12 продуктов", () ->
                productPage.selectProductsPerPageOption(12)
                .verifyAmountOfObjectsShowedOnPage(12));
    }
}
