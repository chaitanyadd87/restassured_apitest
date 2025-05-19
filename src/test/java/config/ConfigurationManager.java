package config;

import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    private static Properties properties = new Properties();

    static {
        try {
            properties.load(ConfigurationManager.class.getResourceAsStream("/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file.", e);
        }
    }

    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}