package view;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.geometry.*;

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Overall Layout
        VBox layout = new VBox(10);
        layout.setPadding(new Insets(20));

        // Label
        Label titleLabel = new Label("CINEMABUDDY");
        titleLabel.setStyle("-fx-font-size: 20px;");

        // Button Box
        HBox buttonBox = new HBox(10);
        buttonBox.setAlignment(Pos.CENTER_RIGHT);

        // Login Button
        Button loginButton = new Button("LOGIN");
        loginButton.setStyle("-fx-pref-width: 100px;");

        // Register Button
        Button registerButton = new Button("REGISTER");
        registerButton.setStyle("-fx-pref-width: 100px;");

        // Add the buttons to the HBox
        buttonBox.getChildren().addAll(loginButton, registerButton);

        // Login form
        VBox loginForm = new VBox(10);
        loginForm.setPadding(new Insets(10));

        // email label
        Label emailLabel = new Label("Email");

        // Create the email text field
        TextField emailField = new TextField();

        // Create the password label
        Label passwordLabel = new Label("Password");

        // Create the password field
        PasswordField passwordField = new PasswordField();

        // Create the login button
        Button formLoginButton = new Button("LOGIN");
        formLoginButton.setStyle("-fx-pref-width: 100px;");

        // Add the login form elements to the VBox
        loginForm.getChildren().addAll(emailLabel, emailField, passwordLabel, passwordField, formLoginButton);

        // Add the title, button box, and login form to the main layout
        layout.getChildren().addAll(titleLabel, buttonBox, loginForm);

        // Create the scene
        Scene scene = new Scene(layout, 400, 300);

        // Set the scene on the stage and show the stage
        primaryStage.setScene(scene);
        primaryStage.setTitle("CinemaBuddy Login");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
