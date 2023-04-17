package data.database;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String dbName = "dolibarr";
    private static final String host = "localhost";
    private static final String username = "root";
    private static final String password = "root";
    private static final String url = "jdbc:mysql://:3306/dolibarr";
    private static final String pathToBackup = "C:/wamp64/bin/mysql/mysql5.7.36/data/backup/dolibarr.sql";
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
        ProcessBuilder pb = new ProcessBuilder("mysql", "--user=" + username, "--password=" + password, "--host=" + host,
                "--execute=source " + pathToBackup, dbName);
        Process process = pb.start();
    }

    public void closeConnection() throws SQLException {
        connection.close();
    }
}
