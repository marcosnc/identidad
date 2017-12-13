package org.identidad.matriculas;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Properties;

public class Configuration {

    private static final String CONFIGURATION_FILE = "identidad.properties";
    private static final String DEFAULT_FILE_STORE = "file-store.txt";

    private static final String PROPERTY_PROXY_HOST = "proxyHost";
    private static final String PROPERTY_PROXY_PORT = "proxyPort";
    private static final String PROPERTY_FILE_STORE = "fileStore";

    private Properties properties;

    public Configuration() {
        loadProperties();
    }

    public boolean isProxyConfigured() {
        return !getProxyHost().isEmpty();
    }
    public String getProxyHost() {
        return properties.getProperty(PROPERTY_PROXY_HOST, "");
    }
    public int getProxyPort() {
        return Integer.parseInt(properties.getProperty(PROPERTY_PROXY_PORT, "0"));
    }

    public void loadProperties() {
        try {
            Properties newProperties = new Properties();
            newProperties.load(new FileInputStream(CONFIGURATION_FILE));
            properties = newProperties;
        } catch (IOException e) {
            System.err.printf("No puedo leer del archivo de configuración (%s): %s", CONFIGURATION_FILE, e.getMessage());
            e.printStackTrace();
        }
    }

    public void saveProperties() {
        try {
            properties.store(new FileOutputStream(CONFIGURATION_FILE), null);
        } catch (IOException e) {
            System.err.printf("No puedo escribir en el archivo de configuración (%s): %s", CONFIGURATION_FILE, e.getMessage());
            e.printStackTrace();
        }
    }

    public Path getFileStore() {
        return Paths.get(properties.getProperty(PROPERTY_FILE_STORE, DEFAULT_FILE_STORE));
    }
}
