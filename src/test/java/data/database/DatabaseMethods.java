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

    public DatabaseMethods() throws SQLException {
        databaseConnection = DatabaseConnection.getInstance();
        statement = databaseConnection.connection.createStatement();
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
}
