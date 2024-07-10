package qa.guru.web.data;

import org.aeonbits.owner.ConfigFactory;

public class Users {
    public static TestData config = ConfigFactory.create(TestData.class, System.getProperties());

    public static final User standart_user = new User(
            config.getUserEmail(),
            config.getUserPassword(),
            config.getFirstName(),
            config.getLastName());
}
