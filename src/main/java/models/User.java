package models;
import lombok.Getter;
import lombok.Setter;

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
    jobTitle,
    login,
    password;

}
