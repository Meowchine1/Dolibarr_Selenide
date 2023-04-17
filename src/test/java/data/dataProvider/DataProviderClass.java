package data.dataProvider;

import data.factory.models.InnerUser;
import data.inputReader.InnerUserFactory;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class DataProviderClass {
    private static Object[][] realUsers;

    private static boolean isUserNotEmpty(InnerUser user){
        if(user.getName().length() > 0 & !user.getName().contains(" ")){
         if(user.getLogin().length() > 0 & !user.getLogin().contains(" ")){
             if(user.getLastname().length() > 0 & !user.getLastname().contains(" ")){
                 if(user.getEmail().length() > 0 & !user.getEmail().contains(" ")){
                     return user.getPhone().length() > 0 & !user.getPhone().contains(" ");
                 }
             }
         }
        }
        return false;
    }
    @DataProvider(name = "validUsers")
    public static Object[][] validUsersData() throws IOException {
        InnerUserFactory validUserFactory = new InnerUserFactory("src/test/java/data/inputReader/valid_user_out.xlsx");
        ArrayList<InnerUser> validUserList = new ArrayList<>();
        validUserFactory.userGeneration(validUserList);
        Object[][] result = new Object[validUserList.size()][1];
        ArrayList<InnerUser> normalUsers = new ArrayList<>();
        int i = 0;
        for (InnerUser user : validUserList) {
            result[i][0] = user;

           /* if(isUserNotEmpty(user)){
                normalUsers.add(user);
            }*/
            i++;
        }
        realUsers = result;
        return result;

    }
    @DataProvider(name= "searchUser")
    public static Object[][] searchUserData(){
        return realUsers;
    }

    @DataProvider(name = "invalidUsers")
    public static Object[][] invalidUsersData() throws IOException {
        InnerUserFactory invalidUserFactory = new InnerUserFactory("src/test/java/data/inputReader/invalid_user_out.xlsx");
        ArrayList<InnerUser> invalidUserList = new ArrayList<>();
        invalidUserFactory.userGeneration(invalidUserList);

        Object[][] result = new Object[invalidUserList.size()][1];
        int i = 0;
        for (InnerUser user : invalidUserList) {
            result[i][0] = user;
            i++;
        }
        return result;

    }


}
