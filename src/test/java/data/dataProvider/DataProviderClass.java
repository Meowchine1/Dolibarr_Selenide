package data.dataProvider;

import data.factory.models.InnerUser;
import data.inputReader.InnerUserFactory;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;

public class DataProviderClass {

    @DataProvider(name = "validUsers")

    public static Object[][] dataProviderMethod() throws IOException {
        InnerUserFactory validUserFactory = new InnerUserFactory("src/test/java/data/inputReader/valid_user_out.xlsx");
        ArrayList<InnerUser> validUserList = new ArrayList<>();
        validUserFactory.userGeneration(validUserList);

        Object[][] result = new Object[validUserList.size()][1];
        int i = 0;
        for (InnerUser user : validUserList) {
            result[i][0] = user;
            i++;
        }
        return result;

    }

}
