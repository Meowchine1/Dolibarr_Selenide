package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
Создание пользователя -- получить пользователя из таблицы: Table llx_user
Создание пролдукта -- получить продлукт из таблицы:

*/
public class DatabaseMethods {

    private DatabaseConnection databaseConnection = DatabaseConnection.getInstance();
    String selectQuery = "select * from dolibarr.llx_user;";

    public DatabaseMethods() throws SQLException {
    }

    public void getAllUsers() throws SQLException {
        Statement statement = databaseConnection.connection.createStatement();
        ResultSet rs = statement.executeQuery(selectQuery);
        while (rs.next()){
            System.out.println("id = "+rs.getString("login"));
        }
    }
}
