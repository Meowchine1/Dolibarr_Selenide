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
    private static String table = "llx_user";
    private static String filterParam = "login";
    private static String filterParamVal = "root";
    private static String goalParam = "email";
    private static String queryTemplate = "select " + goalParam + " from " + table + " where "+ filterParam +"='"+ filterParamVal +"';";

    public DatabaseMethods() throws SQLException {
        databaseConnection = DatabaseConnection.getInstance();
        statement = databaseConnection.connection.createStatement();
    }

    public void printDbInf() throws SQLException {
        String getAllUsersQuery = queryTemplate;
        ResultSet rs = statement.executeQuery(getAllUsersQuery);
        while (rs.next()) {
        System.out.println(rs.getString(goalParam));
        }
    }

    public boolean testParamValue(String _table, String _goalParam, String _goalParamVal, String _filterParam, String _filterParamValue ) throws SQLException {
        filterParam = _filterParam;
        filterParamVal = _filterParamValue;
        goalParam = _goalParam;
        table = _table;
        String testQuery = queryTemplate;
        ResultSet rs = statement.executeQuery(testQuery);
        while (rs.next()){
            return rs.getString(_goalParam).equals(_goalParamVal);
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

    }

}
