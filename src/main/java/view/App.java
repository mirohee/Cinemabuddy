package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import api.api;

import java.io.IOException;

public class App extends Application {

    private Label ratingLabel; // Label to display movie rating

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main view from FXML
        Parent root = FXMLLoader.load(getClass().getResource("/MainView.fxml"));

        // Create a VBox to hold UI elements
        VBox vbox = new VBox();
        vbox.getChildren().add(root);

        // Create a Label to display movie rating
        ratingLabel = new Label();
        vbox.getChildren().add(ratingLabel);

        // Set up the scene
        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("JavaFX Application");
        primaryStage.show();

        // Test API call
        testApiCall();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void testApiCall() {
        // Create an instance of ApiService
        api api = new api();

        try {
            // Sample movie ID (replace with a valid movie ID from the API)
            String movieId = "tt0000111";

            // Call the fetchMovieRatingById method
            String ratingResponse = api.fetchMovieRatingById(movieId);

            // Update the ratingLabel text with the movie rating response
            ratingLabel.setText("Movie Rating: " + ratingResponse);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
