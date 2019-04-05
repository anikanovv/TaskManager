package ru.anikanov.tm.utils;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.Properties;

public class PropertiesUtil {
    public static Properties getProperties() {
        @NotNull final Properties property = new Properties();
        try {
            property.load(PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return property;
    }
}
