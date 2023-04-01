package data.factory.models;
import data.factory.models.config.Sex;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InnerUser extends User{
    String  lastname,
            login,
            password,
            fax;
    Boolean isAdmine;
    Boolean isEmployee;

}
