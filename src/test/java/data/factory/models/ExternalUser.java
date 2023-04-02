package data.factory.models;

import data.factory.models.config.Visibility;
import lombok.AllArgsConstructor;
import lombok.Getter;


@AllArgsConstructor
@Getter
public class ExternalUser extends User{
    Visibility visibility;

}
