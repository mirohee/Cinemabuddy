package util;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.control.ComboBox;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

public class LanguageManagerTest {

    private LanguageManager languageManager;

    @BeforeEach
    public void setUp() {
        languageManager = LanguageManager.getInstance();
    }

    @Test
    public void getInstanceNotNull() {
        assertNotNull(languageManager);
    }

    @Test
    public void getStringValidKey() {
        String key = "login";
        String expected = "Login";
        String actual = languageManager.getString(key);
        assertEquals(expected, actual);
    }

    @Test
    public void loadLanguageValidLocale() {
        String lang = "en";
        String country = "UK";
        languageManager.loadLanguage(lang, country);
        ResourceBundle bundle = languageManager.getResourceBundle();
        assertNotNull(bundle);
    }

    @Test
    public void changeLanguageValidLocale() {
        String lang = "fa";
        String country = "IR";
        languageManager.changeLanguage(lang, country);
        ResourceBundle bundle = languageManager.getResourceBundle();
        assertNotNull(bundle);
    }

    @Test
    public void addLanguageSelectorValid() {
        ComboBox<String> languageSelector = new ComboBox<>();
        languageManager.addLanguageSelector(languageSelector);
        assertNotNull(languageSelector);
    }

    @Test
    public void testHandleLanguageSelection() {
        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("English", "Persian", "Finnish");
        comboBox.setValue("English");
        languageManager.handleLanguageSelection(new ActionEvent(comboBox, null));
        assertEquals("en", languageManager.getLocale().getLanguage());
        assertEquals("UK", languageManager.getLocale().getCountry());

        comboBox.setValue("Persian");
        languageManager.handleLanguageSelection(new ActionEvent(comboBox, null));
        assertEquals("fa", languageManager.getLocale().getLanguage());
        assertEquals("IR", languageManager.getLocale().getCountry());

        comboBox.setValue("Finnish");
        languageManager.handleLanguageSelection(new ActionEvent(comboBox, null));
        assertEquals("fin", languageManager.getLocale().getLanguage());
        assertEquals("FI", languageManager.getLocale().getCountry());

        comboBox.setValue("");
        languageManager.handleLanguageSelection(new ActionEvent(comboBox, null));
        assertEquals("en", languageManager.getLocale().getLanguage());
        assertEquals("UK", languageManager.getLocale().getCountry());

    }


}

