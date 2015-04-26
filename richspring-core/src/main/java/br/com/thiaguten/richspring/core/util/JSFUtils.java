package br.com.thiaguten.richspring.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import java.util.Iterator;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

public abstract class JSFUtils {

    private static final Logger log = LoggerFactory.getLogger(JSFUtils.class);

    public static FacesContext getFacesContext() {
        return FacesContext.getCurrentInstance();
    }

    public static UIViewRoot getViewRoot() {
        if (getFacesContext() != null) {
            return getFacesContext().getViewRoot();
        }
        return null;
    }

    public static Locale getViewRootLocale() {
        if (getViewRoot() != null) {
            return getViewRoot().getLocale();
        }
        return null;
    }

    public static void setViewRootLocale(Locale locale) {
        if (getViewRoot() != null && locale != null) {
            getViewRoot().setLocale(locale);
        }
    }

    public static void cleanSubmittedValues(UIComponent component) {
        if (component instanceof EditableValueHolder) {
            EditableValueHolder evh = (EditableValueHolder) component;
            evh.setSubmittedValue(null);
            evh.setValue(null);
            evh.setLocalValueSet(false);
            evh.setValid(true);
            if (component.getChildCount() > 0) {
                component.getChildren().forEach(br.com.thiaguten.richspring.core.util.JSFUtils::cleanSubmittedValues);
            }
        }
    }

    // message bundle
    public static void addSaveInfoMessage() {
        addInfoMessage("MSG001");
    }

    public static void addUpdateInfoMessage() {
        addInfoMessage("MSG002");
    }

    public static void addDeleteInfoMessage() {
        addInfoMessage("MSG003");
    }

    public static void addInfoMessage(String key) {
        addMessage(key, FacesMessage.SEVERITY_INFO);
    }

    public static void addWarnMessage(String key) {
        addMessage(key, FacesMessage.SEVERITY_WARN);
    }

    public static void addErroMessage(String key) {
        addMessage(key, FacesMessage.SEVERITY_ERROR);
    }

    public static void addMessage(String key, Severity severity) {
        if (getFacesContext() != null) {
            getFacesContext().addMessage(null, new FacesMessage(severity, getMessage(key), key));
        }
    }

    public static boolean hasMessage(String key) {
        boolean hasMessage = false;
        if (getFacesContext() != null) {
            Iterator<FacesMessage> messages = getFacesContext().getMessages();
            while (messages.hasNext()) {
                FacesMessage message = messages.next();
                hasMessage = message.getDetail().equals(key);
            }
        }
        return hasMessage;
    }

    public static String getMessage(String key) {
        if (getFacesContext() != null) {
            String messageBundle = getFacesContext().getApplication().getMessageBundle();
            return getMessage(messageBundle, key);
        }
        return null;

    }

    public static String getMessage(String baseName, String key) {
        if (getViewRootLocale() != null) {
            Locale locale = getViewRootLocale();
            ResourceBundle resourceBundle = ResourceBundle.getBundle(baseName, locale);

            String message = "???";
            try {
                message = resourceBundle.getString(key);
            } catch (MissingResourceException e) {
                log.error("Key '" + key + "' not found in resource bundle '" + baseName + "'");
            }
            return message;
        }
        return null;
    }

}