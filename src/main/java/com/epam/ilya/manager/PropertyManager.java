package com.epam.ilya.manager;

import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    public static Properties getProperty(String name) throws IOException {
        Properties properties = new Properties();


        properties.load(PropertyManager.class.getClassLoader().getResourceAsStream("database/database.properties"));


        return properties;
    }
}