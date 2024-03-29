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

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void LoginButtonClicked(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        UserController userController = new UserController();
        if (userController.loginUser(email, password)) {
            // Login successful, perform desired actions
            System.out.println("Login successful!");

            // Load the Register.fxml file
            Parent homePageParent = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));

            // Get the current stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the scene with Register.fxml content
            stage.setScene(new Scene(homePageParent));



        } else {
            // Invalid credentials, show error message or take appropriate action
            System.out.println("Invalid email or password.");

        }
    }
}

