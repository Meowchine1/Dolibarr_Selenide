package data.database;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static String dbName = "dolibarr";
    private static String username = "root";
    private static String password = "root";
    private static String url = "jdbc:mysql://:3306/dolibarr";
    private static String pathToBackup = "C:/wamp64/bin/mysql/mysql5.7.36/data/backup/dolib_back.sql";
    private static DatabaseConnection instance;
    protected Connection connection;

    private DatabaseConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            this.connection = DriverManager.getConnection(url, username, password);
        } catch (ClassNotFoundException | InvocationTargetException | InstantiationException | IllegalAccessException |
                 NoSuchMethodException e) {
            System.out.println("Error name: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    private Connection getConnection() {
        return connection;
    }

    public static DatabaseConnection getInstance() throws SQLException {
        if (instance == null) {
            instance = new DatabaseConnection();
        } else if (instance.getConnection().isClosed()) {
            instance = new DatabaseConnection();
        }

        return instance;
    }

    public void setBackup() throws SQLException, IOException {
       Statement statement = connection.createStatement();
       statement.executeUpdate("DROP DATABASE " + dbName);
       statement.executeUpdate("CREATE DATABASE " + dbName);
       Process process = new ProcessBuilder("mysql", "-u", username, "-p", password, dbName, "<", pathToBackup ).start(); // не работает
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
