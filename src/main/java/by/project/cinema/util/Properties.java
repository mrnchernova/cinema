package by.project.cinema.util;

import java.io.IOException;
import java.io.InputStream;

public final class Properties {

    public static final java.util.Properties PROPERTIES = new java.util.Properties();


    static {
        loadProperties();
    }

    public static String get(String key){
        return PROPERTIES.getProperty(key);
    }

    private static void loadProperties() {
        try (InputStream stream = Properties.class.getClassLoader().getResourceAsStream("application.properties")) {
            PROPERTIES.load(stream);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    private Properties() {

    }
}
