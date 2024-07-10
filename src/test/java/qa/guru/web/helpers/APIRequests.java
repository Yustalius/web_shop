package qa.guru.web.helpers;

import io.qameta.allure.Step;
import qa.guru.web.data.User;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static qa.guru.web.specs.CartSpec.cartResponseSpec;
import static qa.guru.web.specs.LoginSpec.loginRequestSpec;
import static qa.guru.web.specs.LoginSpec.loginResponseSpec;
import static qa.guru.web.specs.CartSpec.cartRequestSpec;

public class APIRequests {

    public static final String addToCartEndpoint = "/addproducttocart/details/%s/1";

    public static String authAPI(User user) {
        String authCookie = step("Получить токен авторизации", () ->
                given(loginRequestSpec)
                        .formParam("Email", user.getEmail())
                        .formParam("Password", user.getPassword())
                        .when()
                        .post("/login")
                        .then()
                        .spec(loginResponseSpec)
                        .extract()
                        .cookie("NOPCOMMERCE.AUTH"));

        return authCookie;
    }

    @Step("Добавить продукт в корзину")
    public static void addToCartAPI(User user, int productId, int quantity) {
        given(cartRequestSpec)
                .cookie("NOPCOMMERCE.AUTH", authAPI(user))
                .formParam("addtocart_" + productId + ".EnteredQuantity", quantity)
                .when()
                .post(format(addToCartEndpoint, productId))
                .then()
                .spec(cartResponseSpec);
    }

    @Step("Добавить продукт в корзину")
    public static void addToCartAPI(User user, int productId) {
        given(cartRequestSpec)
                .cookie("NOPCOMMERCE.AUTH", authAPI(user))
                .formParam("addtocart_" + productId + ".EnteredQuantity", 1)
                .when()
                .post(format(addToCartEndpoint, productId))
                .then()
                .spec(cartResponseSpec);
    }
}
