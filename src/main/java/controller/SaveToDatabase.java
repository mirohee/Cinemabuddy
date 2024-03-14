package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.text.DecimalFormat;
import java.util.Properties;
import java.io.FileWriter;
import java.io.IOException;

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

    // Method to export data to CSV
    public void exportDataToCSV(String filePath) {
        try (FileWriter csvWriter = new FileWriter(filePath)) {
            // Writing header
            csvWriter.append("UserID,MovieID,ShowID,ActorID,DirectorID,GenreID,PreferenceID\n");

            // Example data fetching and writing logic (you need to implement actual data fetching)
            // Pretend we fetched some data and now are writing it to CSV
            csvWriter.append("1,101,0,201,301,401,501\n"); // Example row, repeat for actual data rows

            csvWriter.flush();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to CSV: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        SaveToDatabase saveToDatabase = new SaveToDatabase();
        saveToDatabase.getConnectionToDb();
        // Example call to export data to CSV
        saveToDatabase.exportDataToCSV("path/to/your/output.csv");
    }
}
