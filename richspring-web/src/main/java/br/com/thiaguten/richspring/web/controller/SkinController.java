package br.com.thiaguten.richspring.web.controller;

import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

@Named
@Scope("session")
public class SkinController implements Serializable {

    private static final long serialVersionUID = -1067189826024156851L;

    private static final List<String> supportedSkins = Arrays.asList("DEFAULT",
            "plain", "emeraldTown", "blueSky", "wine", "japanCherry", "ruby",
            "classic", "deepMarine");

    private String currentSkin = "blueSky";

    public List<String> getSkins() {
        return SkinController.supportedSkins;
    }

    // getters and setters

    public String getCurrentSkin() {
        return currentSkin;
    }

    public void setCurrentSkin(String skin) {
        this.currentSkin = skin;
    }

}
