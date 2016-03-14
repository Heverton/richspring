package br.com.thiaguten.richspring.web.component.filter;

import java.io.Serializable;

import br.com.thiaguten.persistence.entity.BaseEntity;

public interface Filter<T extends BaseEntity<? extends Serializable>> {

    boolean match(T type);

}
