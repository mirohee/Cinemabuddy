package controller;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.App;
import util.LanguageManager;

import java.io.IOException;

/**
 * Controller class for the main view of the application.
 */
public class MainController {

    @FXML
    private TextField searchField;

    @FXML
    private Label showInfoTextArea;

    @FXML
    private VBox resultCardsContainer;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

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
        registerButton.setText(languageManager.getString("register"));
        loginButton.setText(languageManager.getString("login"));
    }

    /**
     * Handles the event when the login button is clicked.
     * @param event The ActionEvent triggered by clicking the login button.
     * @throws IOException If an I/O error occurs while loading the login page.
     */
    @FXML
    private void LoginButtonClicked(ActionEvent event) throws IOException {
        // Load the Login.fxml file
        Parent loginParent = FXMLLoader.load(getClass().getResource("/Login.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Set the scene with Login.fxml content
        stage.setScene(new Scene(loginParent));
    }

    /**
     * Handles the event when the register button is clicked.
     * @param event The ActionEvent triggered by clicking the register button.
     * @throws IOException If an I/O error occurs while loading the register page.
     */
    @FXML
    private void RegisterButtonClicked(ActionEvent event) throws IOException {
        // Load the Register.fxml file
        Parent registerParent = FXMLLoader.load(getClass().getResource("/Register.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Set the scene with Register.fxml content
        stage.setScene(new Scene(registerParent));
    }

    /**
     * The main method to launch the application.
     * @param args Command-line arguments.
     */
    public static void main(String[] args) {
        Application.launch(App.class);
    }
}
