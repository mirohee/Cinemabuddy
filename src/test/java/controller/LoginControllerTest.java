package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import util.LanguageManager;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LoginControllerTest extends ApplicationTest {

    private LanguageManager languageManager;

    @Override
    public void start(Stage stage) throws Exception {
        languageManager = LanguageManager.getInstance();
        languageManager.loadLanguage("en", "UK");
        Parent root = FXMLLoader.load(getClass().getResource("/Login.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void testControllerInitialization() {
        // Test initialization of UI components
        TextField emailField = lookup("#emailField").query();
        PasswordField passwordField = lookup("#passwordField").query();
        Button loginButton = lookup("#loginButton").query();
        ComboBox<String> languageComboBox = lookup("#languageComboBox").query();

        // Verify initial state of UI components
        assertEquals("", emailField.getText());
        assertEquals("", passwordField.getText());
        assertEquals("Login", loginButton.getText());
        assertEquals("English", languageComboBox.getValue());
    }

    @Test
    void testHandleLanguageSelection() {
        // Test language selection handling
        ComboBox<String> languageComboBox = lookup("#languageComboBox").query();

        // Select a different language
        clickOn(languageComboBox).moveBy(0, 40).clickOn(); // Select Persian

        // Verify the selected language
        assertEquals("Persian", languageComboBox.getValue());
    }

    @Test
    void testLoginButtonClicked() {
        // Test login button clicked
        TextField emailField = lookup("#emailField").query();
        PasswordField passwordField = lookup("#passwordField").query();
        Button loginButton = lookup("#loginButton").query();

        // Set the email and password fields
        clickOn(emailField).write("timi@timi.fi");
        clickOn(passwordField).write("kissa");

        // Click on the login button
        clickOn(loginButton);
        // expect the page to be home page
        assertEquals("Search Movies/Series", ((Label) lookup("#searchLabelText").query()).getText());
    }

    @Test
    void testLoginButtonClickedInvalid() {

        TextField emailField = lookup("#emailField").query();
        PasswordField passwordField = lookup("#passwordField").query();
        Button loginButton = lookup("#loginButton").query();

        // Set the email and password fields
        clickOn(emailField).write("");
        clickOn(passwordField).write("");

        // Click on the login button
        clickOn(loginButton);

        // expect the page to be login page
        assertEquals("Login Error", ((Label) lookup("#errorLabel").query()).getText());

    }
}

