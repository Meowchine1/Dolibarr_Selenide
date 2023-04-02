package tests.functionalTests;

import data.database.DatabaseMethods;

import java.io.IOException;
import java.sql.SQLException;

public class testDb {

    public static void main(String[] args) throws SQLException, IOException {
        DatabaseMethods databaseMethods = new DatabaseMethods();
        databaseMethods.setBackup();
        databaseMethods.closeConnection();
    }
}
