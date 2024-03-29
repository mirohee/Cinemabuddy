package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import model.User;

import java.io.IOException;

public class RegisterController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField emailField;

    @FXML
    private TextField ageField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void RegisterButtonClicked(ActionEvent event) throws IOException {
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        String email = emailField.getText();
        String age = ageField.getText();
        String password = passwordField.getText();

        // You can call your UserController.registerUser() method here
        UserController userController = new UserController();
        User newUser = new User(firstName, lastName, email, age, password);

        if (userController.registerUser(newUser)) {
            System.out.println("Login successful!");

            // Load the Register.fxml file
            Parent homePageParent = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));

            // Get the current stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the scene with Register.fxml content
            stage.setScene(new Scene(homePageParent));

        } else {
            // Registration failed, show error message or take appropriate action
            System.out.println("Registration failed.");
        }
    }
}
