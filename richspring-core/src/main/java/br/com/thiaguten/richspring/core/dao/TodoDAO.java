package br.com.thiaguten.richspring.core.dao;

import br.com.thiaguten.persistence.core.Persistence;
import br.com.thiaguten.richspring.model.Todo;

import java.util.List;

public interface TodoDAO extends Persistence<Long, Todo> {

    List<Todo> find(Todo todo);

}
