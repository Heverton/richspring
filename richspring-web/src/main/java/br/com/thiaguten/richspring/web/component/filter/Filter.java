package br.com.thiaguten.richspring.web.component.filter;

import br.com.thiaguten.persistence.entity.BaseEntity;

public interface Filter<T extends BaseEntity> {

    boolean match(T type);

}
