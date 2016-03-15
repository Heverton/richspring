package br.com.thiaguten.richspring.web.component.filter;

import br.com.thiaguten.persistence.entity.BaseEntity;

import java.io.Serializable;

public interface Filter<T extends BaseEntity<? extends Serializable>> {

    boolean match(T type);

}
