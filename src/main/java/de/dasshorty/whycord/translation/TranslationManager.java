package de.dasshorty.whycord.translation;

import java.util.Locale;
import java.util.ResourceBundle;

public class TranslationManager {

    private final Locale defaultLocale = Locale.of(System.getenv("DEFAULT_LOCAL"));


    public String translate(String key) {
        return this.translate(this.defaultLocale, key);
    }

    public String translate(Locale locale, String key) {
        ResourceBundle resourceBundle = this.getResourceBundle(locale);

        if (!resourceBundle.containsKey(key)) {
            return key;
        }

        return this.replacePattern(resourceBundle.getString(key));
    }

    private String replacePattern(String input) {
        return input.replace("<s>", "~~")
                .replace("</s>", "~~")
                .replace("<u>", "__")
                .replace("</u>", "__")
                .replace("<b>", "**")
                .replace("</b>", "**")
                .replace("<i>", "*")
                .replace("</i>", "*");
    }

    private ResourceBundle getResourceBundle(Locale locale) {
        return ResourceBundle.getBundle("translation", locale);
    }

}
