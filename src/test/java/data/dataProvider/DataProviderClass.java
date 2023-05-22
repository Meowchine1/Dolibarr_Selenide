package data.dataProvider;

import data.inputReader.Analyzer;
import models.Company;
import models.InnerUser;
import org.testng.annotations.DataProvider;
import utils.Config;

import java.io.IOException;
import java.util.ArrayList;

public class DataProviderClass {
    private static Object[][] realUsers;
    private static Object[][] validCompany;
    private static Object[][] invalidCompany;
    private static ArrayList<String> realLogins = new ArrayList<>();
    private static ArrayList<String> realNames = new ArrayList<>();
    private static ArrayList<String> realSurnames = new ArrayList<>();
    private static ArrayList<String> realPhones = new ArrayList<>();

    private static boolean ifNameIsNotEmpty(InnerUser user) {
        return user.getName().length() > 0 & !user.getName().contains(" ");
    }

    private static boolean ifSurameIsNotEmpty(InnerUser user){
        return user.getLastname().length() > 0 & !user.getLastname().contains(" ");
    }
    private static boolean ifLoginIsNotEmpty(InnerUser user){
        return user.getLogin().length() > 0 & !user.getLogin().contains(" ");
    }
    private static boolean ifPhoneIsNotEmpty(InnerUser user){
        return user.getPhone().length() > 0 & !user.getPhone().contains(" ");
    }

    @DataProvider(name = "oneValidUser")
    public static Object[][] oneValidUser(){
        Object[][] res = new Object[1][1];
        res[0][0] = realUsers[0][0];
        return res;
    }
@DataProvider(name = "validCompanyData")
public static Object[][] validCompanyData() throws IOException {
    Analyzer analyzer = new Analyzer(Config.VALID_COMPANY_PATH);
    ArrayList<Company> validCompanyList = new ArrayList<>();
    analyzer.companyGeneration(validCompanyList);
    validCompany = new Object[validCompanyList.size()][1];
    int i = 0;
    for (Company company : validCompanyList) {
        validCompany[i][0] = company;
    }
    return validCompany;
}

    @DataProvider(name = "invalidCompanyData")
    public static Object[][] invalidCompanyData() throws IOException {
        Analyzer analyzer = new Analyzer(Config.INVALID_COMPANY_PATH);
        ArrayList<Company> invalidCompanyList = new ArrayList<>();
        analyzer.companyGeneration(invalidCompanyList);
        invalidCompany = new Object[invalidCompanyList.size()][1];
        int i = 0;
        for (Company company : invalidCompanyList) {
            invalidCompany[i][0] = company;
        }
        return invalidCompany;
    }


    @DataProvider(name = "validUsers")
    public static Object[][] validUsersData() throws IOException {
        Analyzer analyzer = new Analyzer(Config.VALID_USERS_PATH);
        ArrayList<InnerUser> validUserList = new ArrayList<>();
        analyzer.userGeneration(validUserList);
        realUsers = new Object[validUserList.size()][1];
        int i = 0;
        for (InnerUser user : validUserList) {
            realUsers[i][0] = user;

            if (ifNameIsNotEmpty(user)){
                realNames.add(user.getName());
            }
            if(ifSurameIsNotEmpty(user)) {
                realSurnames.add(user.getLastname());
            }
            if(ifLoginIsNotEmpty(user)){
                realLogins.add(user.getLogin());
            }
            if(ifPhoneIsNotEmpty(user)){
                realPhones.add(user.getPhone());
            }
            i++;
        }
        return realUsers;
    }

    @DataProvider(name= "searchUserLogin")
    public static Object[][] searchUserLogin(){
        return fromArrayToObject(realLogins);
    }
    @DataProvider(name= "searchUserName")
    public static Object[][] searchUserName(){
        return fromArrayToObject(realNames);
    }
    @DataProvider(name= "searchUserSurname")
    public static Object[][] searchUserSurname(){
        return fromArrayToObject(realSurnames);
    }
    @DataProvider(name= "searchUserPhone")
    public static Object[][] searchUserPhone(){
        return fromArrayToObject(realPhones);
    }

    @DataProvider(name = "invalidUsers")
    public static Object[][] invalidUsersData() throws IOException {
        Analyzer invalidUserFactory = new Analyzer(Config.INVALID_USERS_PATH);
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

    private static Object[][] fromArrayToObject(ArrayList<String> inputData){
        Object[][] data = new Object[inputData.size()][1];
        int i = 0;
        for(String value : inputData){
            data[i][0] = value;
            i++;
        }
        return data;
    }




}
