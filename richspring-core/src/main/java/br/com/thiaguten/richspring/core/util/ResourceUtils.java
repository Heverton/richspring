package br.com.thiaguten.richspring.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

public abstract class ResourceUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceUtils.class);

    public static ResourceBundle getResource(String resource) {
        ResourceBundle resourceBundle = null;
        try {
            resourceBundle = ResourceBundle.getBundle(resource);
        } catch (Exception e) {
            LOGGER.error("Erro ao tentar carregar o recurso " + resource + ".properties", e);
        }
        return resourceBundle;
    }

    public static PropertyResourceBundle getPropertyResourceBundle(InputStream inputStream) {
        PropertyResourceBundle propertyResourceBundle = null;
        try {
            propertyResourceBundle = new PropertyResourceBundle(inputStream);
        } catch (Exception e) {
            LOGGER.error("Erro ao tentar carregar o stream", e);
        }
        return propertyResourceBundle;
    }

    public static Properties convertResourceBundleToProperties(ResourceBundle resource) {
        Properties properties = null;
        if (resource != null) {
            properties = new Properties();
            Enumeration<String> keys = resource.getKeys();
            while (keys.hasMoreElements()) {
                String key = keys.nextElement();
                properties.put(key, resource.getString(key));
            }
        }
        return properties;
    }

}
