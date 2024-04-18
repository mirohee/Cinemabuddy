package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testfx.framework.junit5.ApplicationTest;
import util.LanguageManager;

import static org.junit.jupiter.api.Assertions.*;

public class MainControllerTest extends ApplicationTest {


    @BeforeAll
    public static void setUp() {
        System.out.println("Setting up...");
        // Add any setup needed for the tests
    }

    @Override
    public void start(Stage stage) throws Exception {
        LanguageManager languageManager = LanguageManager.getInstance();
        languageManager.loadLanguage("en", "UK");
        Parent root = FXMLLoader.load(getClass().getResource("/MainView.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    public void testControllerInitialization() {
        // Test initialization of UI components
        ComboBox<String> languageComboBox = lookup("#languageComboBox").query();
        Button loginButton = lookup("#loginButton").query();
        Button registerButton = lookup("#registerButton").query();

        assertEquals("Select Language", languageComboBox.getPromptText());
        assertEquals("Login", loginButton.getText());
        assertEquals("Register", registerButton.getText());
    }

    @Test
    public void testHandleLanguageSelection() {
        // Test language selection handling
        ComboBox<String> languageComboBox = lookup("#languageComboBox").query();

        // Select a different language
        clickOn(languageComboBox).moveBy(0, 40).clickOn(); // Select Persian

        // Verify the selected language
        assertEquals("Persian", languageComboBox.getValue());
    }

    @Test
    public void testLoginButtonClicked() {
        // Test login button click
        Button loginButton = lookup("#loginButton").query();
        clickOn(loginButton);

        // expect the page to be login page
        assertNotNull(lookup("#emailField").query());
        assertNotNull(lookup("#passwordField").query());

    }

    @Test
    public void testRegisterButtonClicked() {
        // Test register button click
        Button registerButton = lookup("#registerButton").query();
        clickOn(registerButton);

        // expect the page to be register page
        assertNotNull(lookup("#firstNameField").query());
        assertNotNull(lookup("#lastNameField").query());
        assertNotNull(lookup("#emailField").query());
        assertNotNull(lookup("#ageField").query());
        assertNotNull(lookup("#passwordField").query());

    }
}


