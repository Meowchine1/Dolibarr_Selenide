package data.database;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
Создание пользователя -- получить пользователя из таблицы: Table llx_user
Создание пролдукта -- получить продукт из таблицы:

*/
public class DatabaseMethods {

    private DatabaseConnection databaseConnection;
    private static  Statement statement;
    private static final String USER_TABEL = "llx_user";
    private static final String THIRDPARTIES_TABEL = "llx_societe";

    public DatabaseMethods() throws SQLException {
        databaseConnection = DatabaseConnection.getInstance();
        statement = databaseConnection.connection.createStatement();
    }

    public void executeQuery(String query) throws SQLException {
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            System.out.println(rs.getString("lastname"));
        }

    }
    private static String setQuery(String _table, String _goalParam, String _filterParam, String _filterParamValue ){
        return "select " + _goalParam + " from " + _table + " where "+ _filterParam +"='"+ _filterParamValue +"';";
    }

    public int dbSearchResults(String _table, String _filterParam, String _filterParamValue ) throws SQLException {

        String query = setQuery(_table, "*", _filterParam, _filterParamValue ) ;
        System.out.println(query);
        ResultSet rs = statement.executeQuery(query);
        int count = 0;
        while (rs.next()){
            count ++;

        }
        return count;
    }

    public boolean testParamValue(String _table, String _goalParam, String _goalParamVal, String _filterParam, String _filterParamValue ) throws SQLException {

        String query = setQuery(_table, _goalParam, _filterParam, _filterParamValue ) ;
        System.out.println(query);
        ResultSet rs = statement.executeQuery(query);
        if (rs.next()){
           // return rs.getString(_goalParam).equals(_goalParamVal);
            String actualVal = rs.getString(_goalParam);
            String expectedVal = _goalParamVal;
            if (actualVal.equals(expectedVal) ){
                return true;
            }
            else{
                DbLogger.log("\nParameter : " +_goalParam + "\nExpected: " + expectedVal + "\nActual:" + actualVal);
                return false;
            }
        }
         return false;
    }

    public void getAllUsers() throws SQLException {
        String getAllUsersQuery = "select * from dolibarr.llx_user;";
        ResultSet rs = statement.executeQuery(getAllUsersQuery);
        while (rs.next()){
            System.out.println("login = " + rs.getString("login"));
            System.out.println("password = " + rs.getString("pass"));
        }
    }

    public boolean isUserExist(String login) throws SQLException {
        String getUserByLoginQuery = "select * from llx_user where login='" + login + "';";
        ResultSet rs = statement.executeQuery(getUserByLoginQuery);
        return rs.next();
    }

    public void setBackup() throws SQLException, IOException {
        databaseConnection.setBackup();

    }

    public void closeConnection() throws SQLException {
        databaseConnection.closeConnection();
    }

    public static void main(String[] args) throws SQLException {
        String query = "select lastname from llx_user where login='root';";
        DatabaseMethods databaseMethods = new DatabaseMethods();
        databaseMethods.testParamValue("llx_user", "lastname", "SuperAdmin", "login", "root");

    }

}
