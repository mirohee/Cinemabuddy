package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import view.App;
import api.TvApi;
import model.Show;
import model.ShowParser;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import util.LanguageManager;

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

    private LanguageManager languageManager;
    @FXML
    public void initialize() {
        languageManager = new LanguageManager();
        languageManager.initialize();
        System.out.println("MainController initialized");

        localizeUI();
    }
    private void localizeUI() {
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
    public static void main (String[]args){
        App.launch(App.class);
    }
}



