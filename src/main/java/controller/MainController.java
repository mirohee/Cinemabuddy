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

    }

    @FXML
    private void signInButtonClicked(ActionEvent event) throws IOException {
        // Load the Homepage.fxml file
        Parent homePageParent = FXMLLoader.load(getClass().getResource("/Login.fxml"));

        // Get the current stage
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();

        // Set the scene with Homepage.fxml content
        stage.setScene(new Scene(homePageParent));
    }

    public static void main(String[] args) {
        App.launch(App.class);
    }
}
