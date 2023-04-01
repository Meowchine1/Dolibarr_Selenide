package data.factory.dataGeneration;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import com.github.javafaker.Faker;
import data.factory.models.ExternalUser;
import data.factory.models.config.Country;
import data.factory.models.config.Sex;
import data.factory.models.config.Visibility;

import java.util.*;

public class DataFactory {

   // enum Country { COPPER, NICKEL, SILVER }
   // enum Sex { MALE, FEMALE, OTHER }
  //  enum Visibility { PUBLIC, PRIVATE }

    private static final Faker FAKER = new Faker();
    private static final Random RANDOM = new Random();
    private static final List<Country> COUNTRIES =  // считать из файла всех доступных стран
            Collections.unmodifiableList(Arrays.asList(Country.values()));
    private static final int COUNTRIES_SIZE = COUNTRIES.size();

    private static final List<Sex> SEX =
            Collections.unmodifiableList(Arrays.asList(Sex.values()));
    private static final int SEX_SIZE = SEX.size();

    private static final List<Visibility> VISIBILITY =
            Collections.unmodifiableList(Arrays.asList(Visibility.values()));
    private static final int VISIBILITY_SIZE = VISIBILITY.size();


    public static ExternalUser getExternalUser(){
        Fixture.of(ExternalUser.class).addTemplate("valid", new Rule(){{

            add("name", FAKER.name());
            add("email", FAKER.internet().emailAddress());
            add("address", FAKER.address());
            add("zipCode", FAKER.address().zipCode());
            add("city", FAKER.address().city());
            add("phone", FAKER.phoneNumber());
            add("jobTitle", FAKER.job().title());
            add("country", COUNTRIES.get(RANDOM.nextInt(COUNTRIES_SIZE)));


        }});


       return  null;
    }

    public static void main(String[] args) {
        System.out.println(Country.values().length);
    }
}
