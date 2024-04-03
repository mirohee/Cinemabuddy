package util;

import java.util.Locale;

import java.util.ResourceBundle;

public class LanguageManager {

    Locale locale;
    private ResourceBundle bundle;

    public void initialize() {
        loadLanguage("fa", "IR");
    }
    protected void loadLanguage(String lang, String country) {
        Locale locale = new Locale(lang, country);
        bundle = ResourceBundle.getBundle("languages", locale);
    }
    public String getString(String key){
        return bundle.getString(key);
    }
    public void changeLanguage(String lang, String country){
        loadLanguage(lang, country);
    }

}
