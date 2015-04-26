package br.com.thiaguten.richspring.web.controller;

import org.springframework.context.annotation.Scope;

import javax.inject.Named;
import java.io.Serializable;

@Named
@Scope("request")
public class IndexController implements Serializable {

    private static final long serialVersionUID = 5770635750403859904L;

    public String pageIndex() {
        return "/index";
    }

}
