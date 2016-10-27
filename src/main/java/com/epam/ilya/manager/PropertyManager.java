package com.epam.ilya.manager;

import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    public static Properties getProperty(String name) {
        Properties properties = new Properties();

        try {
            properties.load(PropertyManager.class.getClassLoader().getResourceAsStream("database/database.properties"));
        } catch (IOException e) {
            throw new
        }

        return properties;
    }
}