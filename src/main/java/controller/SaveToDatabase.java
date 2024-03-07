package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.util.Properties;

public class SaveToDatabase {
    public Connection getConnectionToDb() {
        String url = "jdbc:mysql://mysql.metropolia.fi:3306/mirosaa";
        String username = "mirosaa";
        String password = "1234";

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(url, username, password);
            if (connection != null) {
                System.out.println("Connected to the database!");
            }
        } catch (SQLException e) {
            System.out.println("Connection failed. Error: " + e.getMessage());
        }
        return connection;
    }


    public static void main(String[] args) {
        SaveToDatabase saveToDatabase = new SaveToDatabase();
        saveToDatabase.getConnectionToDb();
    }
}
