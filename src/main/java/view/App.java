package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class App extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main view from FXML
        Parent root = FXMLLoader.load(getClass().getResource("/MainView.fxml"));

        // Set up the scene
        Scene scene = new Scene(new VBox(root), 800, 600);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Cinemabuddy");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}