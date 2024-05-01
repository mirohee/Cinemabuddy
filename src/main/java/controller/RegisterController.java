package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.User;
import util.LanguageManager;

import java.io.IOException;

/**
 * Controller class for the registration view of the application.
 */
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
    private Button registerButton;

    @FXML
    private Label errorLabel;

    @FXML
    private ComboBox<String> languageComboBox;

    private LanguageManager languageManager;

    private UserController userController = new UserController();

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
        firstNameField.setPromptText(languageManager.getString("firstname"));
        lastNameField.setPromptText(languageManager.getString("lastname"));
        emailField.setPromptText(languageManager.getString("email"));
        ageField.setPromptText(languageManager.getString("age"));
        passwordField.setPromptText(languageManager.getString("password"));
        registerButton.setText(languageManager.getString("register"));
        errorLabel.setText("");
        String errorMessageKey = userController.getErrorMessageKey();
        if (errorMessageKey != null) {
            errorLabel.setText(languageManager.getString(errorMessageKey));
        }
    }

    /**
     * Handles the event when the register button is clicked.
     * @param event The ActionEvent triggered by clicking the register button.
     * @throws IOException If an I/O error occurs while loading the home page.
     */
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
            System.out.println("Registration successful!");

            // Load the HomePage.fxml file
            Parent homePageParent = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));

            // Get the current stage
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

            // Set the scene with HomePage.fxml content
            stage.setScene(new Scene(homePageParent));
        } else {
            // Registration failed, show error message or take appropriate action
            if (userController.getErrorMessageKey().equals("InvalidInput")){
                errorLabel.setText(languageManager.getString("InvalidInput"));
            } else if (userController.getErrorMessageKey().equals("ExistingEmail")){
                errorLabel.setText(userController.getErrorMessage());
            } else {
                errorLabel.setText(languageManager.getString("allFields"));
            }

            System.out.println("Registration failed.");
        }
    }
}
