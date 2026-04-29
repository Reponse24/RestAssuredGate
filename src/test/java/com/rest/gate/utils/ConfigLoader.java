package com.rest.gate.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigLoader {
    public static String setup() throws IOException {
        // Load config.properties
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
        props.load(fis);
        String baseUrl = props.getProperty("base_url");
        return baseUrl;
    }
}
