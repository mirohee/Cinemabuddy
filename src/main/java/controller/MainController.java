package controller;

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

    @FXML
    public void initialize() {
        languageManager = LanguageManager.getInstance();
        languageManager.addLanguageSelector(languageComboBox);
        System.out.println("MainController initialized");
        updateUIWithLocalizedText();
    }

    @FXML
    private void handleLanguageSelection(ActionEvent event) {
        languageManager.handleLanguageSelection(event);
        updateUIWithLocalizedText();
    }

    private void updateUIWithLocalizedText() {
        registerButton.setText(languageManager.getString("register"));
        loginButton.setText(languageManager.getString("login"));
    }

    @FXML
    private void LoginButtonClicked(ActionEvent event) throws IOException {
        // Load the Homepage.fxml file
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/Login.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Set the scene with Homepage.fxml content
        stage.setScene(new Scene(homePageParent));

        // Localization


    }

    @FXML
    private void RegisterButtonClicked(ActionEvent event) throws IOException {
        // Load the Register.fxml file
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/Register.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Set the scene with Register.fxml content
        stage.setScene(new Scene(homePageParent));
    }

    @FXML
    private void HomepageButtonClicked(ActionEvent event) throws IOException {
        // Load the Register.fxml file
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Set the scene with Register.fxml content
        stage.setScene(new Scene(homePageParent));
    }

    public static void main(String[] args) {
        App.launch(App.class);
    }
}




