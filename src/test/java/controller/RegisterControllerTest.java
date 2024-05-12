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

public class RegisterControllerTest extends ApplicationTest {

    private LanguageManager languageManager;

    @BeforeAll
    public static void setUp() {
        UserController userController = new UserController();
        userController.deleteUser("John.Doe@gmail.com");
        System.out.println("Deleted user");
    }



    @Override
    public void start(Stage stage) throws Exception {
        languageManager = LanguageManager.getInstance();
        languageManager.loadLanguage("en", "UK");
        Parent root = FXMLLoader.load(getClass().getResource("/Register.fxml"));
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @Test
    void testControllerInitialization() {
        // Test initialization of UI components
        TextField firstNameField = lookup("#firstNameField").query();
        TextField lastNameField = lookup("#lastNameField").query();
        TextField emailField = lookup("#emailField").query();
        TextField ageField = lookup("#ageField").query();
        PasswordField passwordField = lookup("#passwordField").query();
        Button registerButton = lookup("#registerButton").query();
        ComboBox<String> languageComboBox = lookup("#languageComboBox").query();

        // Verify initial state of UI components
        assertEquals("", firstNameField.getText());
        assertEquals("", lastNameField.getText());
        assertEquals("", emailField.getText());
        assertEquals("", ageField.getText());
        assertEquals("", passwordField.getText());
        assertEquals("Register", registerButton.getText());
        assertEquals("English", languageComboBox.getValue());
        // Add more assertions as needed
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
    void testRegisterButtonClicked() {
        // Test register button click
        TextField firstNameField = lookup("#firstNameField").query();
        TextField lastNameField = lookup("#lastNameField").query();
        TextField emailField = lookup("#emailField").query();
        TextField ageField = lookup("#ageField").query();
        PasswordField passwordField = lookup("#passwordField").query();
        Button registerButton = lookup("#registerButton").query();

        // Set input values
        clickOn(firstNameField).write("John");
        clickOn(lastNameField).write("Doe");
        clickOn(emailField).write("John.Doe@gmail.com");
        clickOn(ageField).write("30");
        clickOn(passwordField).write("password");

        // Click the register button
        clickOn(registerButton);

        // expect the page to be home page
        assertEquals("Search Movies/Series", ((Label) lookup("#searchLabelText").query()).getText());


    }

    @Test
    void testRegisterButtonClickedInvalid() {
        // Test register button click with invalid input
        TextField firstNameField = lookup("#firstNameField").query();
        TextField lastNameField = lookup("#lastNameField").query();
        TextField emailField = lookup("#emailField").query();
        TextField ageField = lookup("#ageField").query();
        PasswordField passwordField = lookup("#passwordField").query();
        Button registerButton = lookup("#registerButton").query();

        // Set input values
        clickOn(firstNameField).write("");
        clickOn(lastNameField).write("Doe");
        clickOn(emailField).write("");
        clickOn(ageField).write("30");
        clickOn(passwordField).write("password");

        // Click the register button
        clickOn(registerButton);

        // expect the page to be register page
        assertEquals("All fields are required", ((Label) lookup("#errorLabel").query()).getText());
    }

}

