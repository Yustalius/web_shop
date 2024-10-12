package qa.guru.web.helpers;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Test;
import qa.guru.web.data.User;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static java.lang.String.format;
import static qa.guru.web.data.UserBuilder.userGenerator;
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

    @Step("Регистрация пользователя")
    public static void registrationAPI(User user) {
        String requestVerificationToken = given()
                .when()
                .get("https://demowebshop.tricentis.com/register")
                .then()
                .log().cookies()
                .extract()
                .cookie("__RequestVerificationToken");

        System.out.println(" ");

        given()
                .when()
                .formParam("__RequestVerificationToken", requestVerificationToken)
                .formParam("Gender", "M")
                .formParam("FirstName", user.getFirstName())
                .formParam("LastName", user.getLastName())
                .formParam("Email", user.getEmail())
                .formParam("Password", user.getPassword())
                .formParam("ConfirmPassword", user.getPassword())
                .formParam("register-button", "Register")
                .post("https://demowebshop.tricentis.com/register")
                .then()
                .log().status()
                .log().cookies()
                .log().headers();

    }

    @Test
    void fasdfas() {
        User user = userGenerator();
        System.out.println(user);


        registrationAPI(user);
    }
}
