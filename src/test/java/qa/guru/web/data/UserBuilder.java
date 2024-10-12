package qa.guru.web.data;

import com.github.javafaker.Faker;
import org.aeonbits.owner.ConfigFactory;

import java.util.Locale;

public class UserBuilder {

    private static final TestData config = ConfigFactory.create(TestData.class, System.getProperties());
    private static final Faker faker = new Faker(new Locale("en"));

    public static User userGenerator() {
        return User.builder()
                .firstName(faker.name().firstName())
                .lastName(faker.name().lastName())
                .email(faker.internet().emailAddress())
                .password(faker.internet().password())
                .build();
    }

    public static final User standart_user = new User(
            config.getUserEmail(),
            config.getUserPassword(),
            config.getFirstName(),
            config.getLastName());
}
