package models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Company {
    String name,
    address,
    zipCode,
    city,
    phone,
    fax,
    email,
    logoPath;
}
