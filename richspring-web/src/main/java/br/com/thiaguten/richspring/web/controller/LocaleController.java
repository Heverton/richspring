package br.com.thiaguten.richspring.web.controller;

import br.com.thiaguten.richspring.core.util.JSFUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.inject.Named;
import java.io.Serializable;
import java.util.*;
import java.util.Map.Entry;

@Named
@Scope("session")
public class LocaleController implements Serializable {

    private static final long serialVersionUID = 2047799903917169174L;

    public static final Map<String, Locale> supportedLocaleMap = new HashMap<>();

    static {
        Iterator<Locale> supportedLocales = JSFUtils.getFacesContext().getApplication().getSupportedLocales();
        while (supportedLocales.hasNext()) {
            final Locale locale = supportedLocales.next();
            final String localeKey = StringUtils.hasText(locale.getDisplayCountry()) ? locale.getDisplayCountry() : locale.getDisplayLanguage();
            supportedLocaleMap.put(localeKey, locale);
        }
    }

    private Locale locale;
    private String country;

    @PostConstruct
    public void init() {
        this.locale = JSFUtils.getViewRootLocale();
        this.country = this.locale != null ? this.locale.toString() : null;
    }

    public Collection<SelectItem> getCountries() {
        Set<SelectItem> itens = new HashSet<>(LocaleController.supportedLocaleMap.size());
        Set<Entry<String, Locale>> entrySet = LocaleController.supportedLocaleMap.entrySet();
        for (Map.Entry<String, Locale> entry : entrySet) {
            itens.add(new SelectItem(entry.getValue().toString(), entry.getKey()));
        }
//        itens.addAll(LocaleController.supportedLocaleMap.entrySet().stream().map(entry -> new SelectItem(entry.getValue().toString(), entry.getKey())).collect(Collectors.toList()));
        return itens;
    }

    public void localeChangeListener(ValueChangeEvent e) {
        String newLocaleValue = e.getNewValue().toString();
        for (Map.Entry<String, Locale> entry : LocaleController.supportedLocaleMap.entrySet()) {
            if (entry.getValue().toString().equals(newLocaleValue)) {
                this.locale = entry.getValue();
                this.country = newLocaleValue;
                JSFUtils.setViewRootLocale(this.locale);
                break;
            }
        }
    }

    // getters and setters

    public Locale getLocale() {
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
