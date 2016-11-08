package com.epam.ilya.manager;

import java.io.IOException;
import java.util.Properties;

/**
 * Class that manage properties
 *
 * @author Ilya_Bondarenko
 */

public class PropertyManager {

    /**
     * Method loads property by path to them
     *
     * @param path to file with properties
     * @return loaded property object
     * @throws IOException
     */
    public static Properties getProperty(String path) throws IOException {
        Properties properties = new Properties();
        properties.load(PropertyManager.class.getClassLoader().getResourceAsStream(path));
        return properties;
    }
}