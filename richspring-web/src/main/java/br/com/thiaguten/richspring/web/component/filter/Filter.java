package br.com.thiaguten.richspring.web.component.filter;

import br.com.thiaguten.richspring.model.base.BaseModel;

import java.io.Serializable;

public interface Filter<T extends BaseModel<? extends Serializable>> {

    boolean match(T type);

}
