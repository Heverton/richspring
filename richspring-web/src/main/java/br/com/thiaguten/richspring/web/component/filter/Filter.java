package br.com.thiaguten.richspring.web.component.filter;

import br.com.thiaguten.richspring.model.base.BaseEntity;

public interface Filter<T extends BaseEntity> {

    public boolean match(T type);

}
