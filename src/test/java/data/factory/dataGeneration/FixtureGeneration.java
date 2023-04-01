package data.factory.dataGeneration;

import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import data.factory.models.ExternalUser;

public class FixtureGeneration {

    public static void main(String[] args) {
        Fixture.of(ExternalUser.class).addTemplate("valid", new Rule(){
            {
                add("id", random(Long.class, range(1L, 200L)));
                add("name", random("Anderson Parra", "Arthur Hirata"));
                add("nickname", random("nerd", "geek"));
                add("email", "${nickname}@gmail.com");
                add("birthday", instant("18 years ago"));

            }
        });


    }

}
