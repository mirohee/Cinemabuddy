package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import view.App;

import java.io.IOException;

public class MainController {


    @FXML
    public void initialize() {
        System.out.println("MainController initialized");
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
    @FXML
    private void SearchButtonClicked(ActionEvent event) throws IOException {
        // Load the Register.fxml file
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/DisplayResults.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Set the scene with Register.fxml content
        stage.setScene(new Scene(homePageParent));
    } @FXML
    private void HistoryButtonClicked(ActionEvent event) throws IOException {
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/WatchHistory.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Set the scene with Register.fxml content
        stage.setScene(new Scene(homePageParent));
    }



        public static void main (String[]args){
            App.launch(App.class);
        }
    }



