package br.com.thiaguten.richspring.core.util;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.component.EditableValueHolder;
import javax.faces.component.UIComponent;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;

public final class JSFUtils {

    private JSFUtils() {
        // suppress default constructor
        // for noninstantiability
        throw new AssertionError();
    }

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
//            	component.getChildren().forEach(br.com.thiaguten.richspring.core.util.JSFUtils::cleanSubmittedValues);
                List<UIComponent> children = component.getChildren();
                for (UIComponent uiComponent : children) {
                    JSFUtils.cleanSubmittedValues(uiComponent);
                }
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

    public static void addDeleteAllInfoMessage() {
        addInfoMessage("MSG010");
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
            ResourceBundle resourceBundle = ResourceUtils.getResource(baseName, locale);
            return ResourceUtils.getString(resourceBundle, key);
        }
        return null;
    }

}
