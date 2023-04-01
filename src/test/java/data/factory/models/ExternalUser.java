package data.factory.models;


import data.factory.models.config.Visibility;
import lombok.AllArgsConstructor;

import lombok.Getter;

import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.text.DateFormat;

@AllArgsConstructor
@Getter
public class ExternalUser extends User{
    Visibility visibility;

}
