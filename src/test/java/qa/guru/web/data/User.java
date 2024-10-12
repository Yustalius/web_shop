package qa.guru.web.data;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@AllArgsConstructor
@Builder
@ToString
public class User {
    String email;
    String password;
    String firstName;
    String lastName;
}
