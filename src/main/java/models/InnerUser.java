package models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InnerUser extends User{
    String  lastname,
            fax;
    Boolean isAdmine;
    Boolean isEmployee;

}
