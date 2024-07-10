package qa.guru.web.specs;


import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static qa.guru.utils.CustomAllureListener.withCustomTemplates;
import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.filter.log.LogDetail.STATUS;
import static org.hamcrest.Matchers.equalTo;

public class CartSpec {


    public static RequestSpecification cartRequestSpec = with()
            .filter(withCustomTemplates())
            .log().headers()
            .log().params()
            .contentType("application/x-www-form-urlencoded; charset=UTF-8")
            .baseUri("https://demowebshop.tricentis.com");

    public static ResponseSpecification cartResponseSpec = new ResponseSpecBuilder()
            .log(STATUS)
            .log(BODY)
            .expectStatusCode(200)
            .expectBody("success", equalTo(true))
            .build();
}
