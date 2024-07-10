package qa.guru.web.data;

import lombok.Data;

@Data
public class User {
    String email;
    String password;
    String firstName;
    String lastName;

    public User(String email, String password, String firstName, String lastName) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }
}
