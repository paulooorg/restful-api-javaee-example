package io.github.paulooorg.api.infrastructure.i18n;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
    private static final ResourceBundle messagesFile = ResourceBundle.getBundle("i18n/messages", Locale.getDefault());

    private static final Logger logger = LogManager.getLogger(Messages.class);

    public static String get(String key) {
        try {
            return messagesFile.getString(key);
        } catch (Exception e) {
            logger.error("Value not found for key {}", key);
            return key;
        }
    }

    public static String get(String key, Object... parameters) {
        try {
            return MessageFormat.format(messagesFile.getString(key), parameters);
        } catch (Exception e) {
            logger.error("Value not found for key {}", key);
            return key;
        }
    }
}
