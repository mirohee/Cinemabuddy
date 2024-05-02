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

/**
 * Controller class for the login view of the application.
 */
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

    /**
     * Initializes the controller. Sets up the language manager and updates UI with localized text.
     */
    @FXML
    public void initialize() {
        languageManager = LanguageManager.getInstance();
        languageManager.addLanguageSelector(languageComboBox);
        updateUIWithLocalizedText();
    }

    /**
     * Handles language selection from the combo box.
     * @param event The ActionEvent triggered by language selection.
     */
    @FXML
    private void handleLanguageSelection(ActionEvent event) {
        languageManager.handleLanguageSelection(event);
        updateUIWithLocalizedText();
    }

    /**
     * Updates UI elements with localized text.
     */
    private void updateUIWithLocalizedText() {
        emailField.setPromptText(languageManager.getString("email"));
        passwordField.setPromptText(languageManager.getString("password"));
        loginButton.setText(languageManager.getString("login"));
    }

    /**
     * Handles the event when the login button is clicked.
     * @param event The ActionEvent triggered by clicking the login button.
     * @throws IOException If an I/O error occurs while loading the home page.
     */
    @FXML
    private void LoginButtonClicked(ActionEvent event) throws IOException {
        String email = emailField.getText();
        String password = passwordField.getText();

        UserController userController = new UserController();
        if (userController.loginUser(email, password)) {
            // Load the HomePage.fxml file
            Parent homePageParent = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));

            // Get the current stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the scene with HomePage.fxml content
            stage.setScene(new Scene(homePageParent));
        } else {
            // Invalid credentials, display error message
            errorLabel.setText(languageManager.getString("loginError"));
        }
    }
}
