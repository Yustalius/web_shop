package qa.guru.web.data;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({
        "classpath:testdata.properties",
        "classpath:user.properties"
})
public interface TestData extends Config {

    @Key("userEmail")
    String getUserEmail();

    @Key("userPassword")
    String getUserPassword();

    @Key("firstName")
    String getFirstName();

    @Key("lastName")
    String getLastName();

    @Key("minimumContentURL")
    String getMinimumContentURL();

    @Key("productName")
    String getProductName();

    @Key("productId")
    int getProductId();

    @Key("secondProductId")
    int getSecondProductId();

    @Key("thirdProductId")
    int getThirdProductId();

    @Key("secondProductName")
    int getSecondProductName();

    @Key("thirdProductName")
    int getThirdProductName();
}
