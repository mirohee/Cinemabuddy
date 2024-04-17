package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import util.LanguageManager;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginButton;

    @FXML
    private Label errorLabel;

    @FXML
    private ComboBox<String> languageComboBox;

    private LanguageManager languageManager;

    @FXML
    public void initialize() {
        languageManager = LanguageManager.getInstance();
        languageManager.addLanguageSelector(languageComboBox);
        updateUIWithLocalizedText();
    }

    @FXML
    private void handleLanguageSelection(ActionEvent event) {
        languageManager.handleLanguageSelection(event);
        updateUIWithLocalizedText();
    }

    private void updateUIWithLocalizedText() {
        emailField.setPromptText(languageManager.getString("email"));
        passwordField.setPromptText(languageManager.getString("password"));
        loginButton.setText(languageManager.getString("login"));
    }

    @FXML
    private void LoginButtonClicked(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        UserController userController = new UserController();
        if (userController.loginUser(email, password)) {
            // Login successful, perform desired actions
            System.out.println("Login successful!");

            // Load the HomePage.fxml file
            Parent homePageParent = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));

            // Get the current stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the scene with HomePage.fxml content
            stage.setScene(new Scene(homePageParent));

        } else {
            // Invalid credentials, show error message or take appropriate action
            errorLabel.setText(languageManager.getString("loginError"));
        }
    }
}