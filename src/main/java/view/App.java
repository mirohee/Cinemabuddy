package view;

import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import api.ApiClient;


import java.io.IOException;

public class App extends Application {
    private static final String MAIN_VIEW_PATH = "/MainView.fxml";

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main view from FXML
        Parent root = FXMLLoader.load(getClass().getResource(MAIN_VIEW_PATH));

        // Set up the scene
        Scene scene = new Scene(new VBox(root), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Application");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}