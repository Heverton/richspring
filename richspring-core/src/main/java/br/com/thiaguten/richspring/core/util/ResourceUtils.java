package br.com.thiaguten.richspring.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.*;

public final class ResourceUtils {

    private static final Logger LOGGER = LoggerFactory.getLogger(ResourceUtils.class);

    private ResourceUtils() {
        // suppress default constructor
        // for noninstantiability
        throw new AssertionError();
    }

    public static ResourceBundle getResource(String resource, Locale locale) {
        ResourceBundle resourceBundle = null;
        try {
            if (locale == null) {
                resourceBundle = ResourceBundle.getBundle(resource);
            } else {
                resourceBundle = ResourceBundle.getBundle(resource, locale);
            }
        } catch (Exception e) {
            LOGGER.error("Could not load the resource " + resource + ".properties", e);
        }
        return resourceBundle;
    }

    public static PropertyResourceBundle getPropertyResourceBundle(InputStream inputStream) {
        PropertyResourceBundle propertyResourceBundle = null;
        try {
            propertyResourceBundle = new PropertyResourceBundle(inputStream);
        } catch (Exception e) {
            LOGGER.error("Could not load the input stream", e);
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

    public static String getString(ResourceBundle resourceBundle, String key) {
        String message = null;
        if (resourceBundle != null && key != null) {
            try {
                message = resourceBundle.getString(key);
            } catch (MissingResourceException e) {
                LOGGER.error("Key '" + key + "' not found in resource bundle.");
            }
        }
        return message;
    }

}
