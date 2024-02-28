package controller;

import view.App;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class MainController {

    @FXML
    private Label greetingLabel;

    @FXML
    public void initialize() {
        greetingLabel.setText("Hello, JavaFX!");
    }

    public static void main(String[] args) {
        App.launch(App.class);
    }
}
