package controller;

import model.User;
import org.mindrot.jbcrypt.BCrypt;
import util.LanguageManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Controller class for user-related operations.
 */
public class UserController {
    private SaveToDatabase saveToDatabase;
    private String errorMessage;
    private String errorMessageKey;
    private LanguageManager languageManager;

    /**
     * Constructor to initialize the UserController.
     */
    public UserController() {
        languageManager = LanguageManager.getInstance();
        this.saveToDatabase = new SaveToDatabase();
    }

    /**
     * Registers a new user.
     * @param user The user object to register.
     * @return True if registration is successful, false otherwise.
     */
    public boolean registerUser(User user) {
        // Check if any of the fields are empty
        if (user.getFirstname().isEmpty() || user.getLastname().isEmpty() || user.getEmail().isEmpty()
                || user.getAge().isEmpty() || user.getPassword().isEmpty()) {
            System.out.println("All fields are required.");
            setErrorMessage("allFields");
            errorMessageKey = "allFields";
            return false;
        }

        // Check if the user with the given email already exists
        if (getUserByEmail(user.getEmail()) != null) {
            System.out.println("User with this email already exists.");
            setErrorMessage("ExistingEmail");
            errorMessageKey = "ExistingEmail";
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
            preparedStatement.setString(5, user.getPassword());

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println("Error registering user: " + e.getMessage());
            setErrorMessage("InvalidInput");
            errorMessageKey = "InvalidInput";
            return false;
        }
    }

    /**
     * Logs in a user with the given email and password.
     * @param email The email of the user.
     * @param password The password of the user.
     * @return True if login is successful, false otherwise.
     */
    public boolean loginUser(String email, String password) {
        User user = getUserByEmail(email);

        if (user != null && password.equals(user.getPassword())) {
            System.out.println("Login successful!");
            return true;
        } else {
            System.out.println("Invalid email or password.");
            return false;
        }
    }

    /**
     * Retrieves a user from the database by email.
     * @param email The email of the user to retrieve.
     * @return The User object if found, null otherwise.
     */
    public User getUserByEmail(String email) {
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

    /**
     * Deletes a user from the database by email.
     * @param email The email of the user to delete.
     * @return True if deletion is successful, false otherwise.
     */
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

    /**
     * Sets the error message.
     * @param message The error message to set.
     */
    public void setErrorMessage(String message) {
        errorMessage = languageManager.getString(message);
    }

    /**
     * Retrieves the error message.
     * @return The error message.
     */
    public String getErrorMessage() {
        return errorMessage;
    }

    /**
     * Retrieves the error message key.
     * @return The error message key.
     */
    public String getErrorMessageKey() {
        return errorMessageKey;
    }
}
