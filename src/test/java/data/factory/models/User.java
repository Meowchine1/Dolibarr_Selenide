package data.factory.models;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.DateFormat;

@Setter
@Getter
public abstract class User {
    String
    name,
    email,
    address,
    zipCode,
    city,
    phone,
    jobTitle;

}
