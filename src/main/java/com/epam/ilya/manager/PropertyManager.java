package com.epam.ilya.manager;

import java.io.IOException;
import java.util.Properties;

public class PropertyManager {
    public static Properties getProperty(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(PropertyManager.class.getClassLoader().getResourceAsStream(path));
        return properties;
    }
}