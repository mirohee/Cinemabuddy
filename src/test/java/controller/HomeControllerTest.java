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
import static org.junit.jupiter.api.Assertions.assertTrue;

public class HomeControllerTest extends ApplicationTest {

        private LanguageManager languageManager;

        @BeforeAll
        public static void setUp() {
            System.out.println("Setting up...");
            // Add any setup needed for the tests
        }

        @Override
        public void start(Stage stage) throws Exception {
            languageManager = LanguageManager.getInstance();
            languageManager.loadLanguage("en", "UK");
            Parent root = FXMLLoader.load(getClass().getResource("/HomePage.fxml"));
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }

        @Test
        void testControllerInitialization() {
            // Test initialization of UI components
            ComboBox<String> languageComboBox = lookup("#languageComboBox").query();
            TextField searchField = lookup("#searchField").query();
            Button searchButton = lookup("#searchButton").query();
            Label searchLabelText = lookup("#searchLabelText").query();

            assertEquals("Select Language", languageComboBox.getPromptText());
            assertEquals("Search", searchButton.getText());
            assertEquals("Search Movies/Series", searchLabelText.getText());
            assertEquals("", searchField.getText());
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

}
