package controller;

import model.User;
import org.mindrot.jbcrypt.BCrypt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserController {

    private SaveToDatabase saveToDatabase;

    public UserController() {
        this.saveToDatabase = new SaveToDatabase();
    }

    public boolean registerUser(User user) {
        // Check if the user with the given email already exists
        if (getUserByEmail(user.getEmail()) != null) {
            System.out.println("User with this email already exists.");
            return false;
        }

        // Insert the new user into the database
        String query = "INSERT INTO User (FirstName, LastName, Email, Age, Password) VALUES (?, ?, ?, ?, ?)";
        try (Connection connection = saveToDatabase.getConnectionToDb();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, user.getFirstname());
            preparedStatement.setString(2, user.getLastname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getAge());
            preparedStatement.setString(5, user.getHashedPassword());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
            return false;
        }
    }

    public boolean loginUser(String email, String password) {
        User user = getUserByEmail(email);

        if (user != null && BCrypt.checkpw(password, user.getHashedPassword())) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid email or password.");

            // Check if user is not null before accessing the hashed password
            if (user != null) {
                System.out.println("Hashed password: " + user.getHashedPassword());
            }

            return false;
        }
    }


    private User getUserByEmail(String email) {
        String query = "SELECT * FROM User WHERE Email = ?";
        try (Connection connection = saveToDatabase.getConnectionToDb();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new User(
                        resultSet.getString("FirstName"),
                        resultSet.getString("LastName"),
                        resultSet.getString("Email"),
                        resultSet.getString("Age"),
                        resultSet.getString("Password")
                );
            }

        } catch (SQLException e) {
            System.out.println("Error getting user by email: " + e.getMessage());
        }
        return null;
    }
    public Boolean deleteUser(String email) {
        String query = "DELETE FROM User WHERE Email = ?";
        try (Connection connection = saveToDatabase.getConnectionToDb();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {

            preparedStatement.setString(1, email);
            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error deleting user: " + e.getMessage());
            return false;
        }
    }

}
