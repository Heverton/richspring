package br.com.thiaguten.richspring.core.dao;

import br.com.thiaguten.persistence.dao.BaseDAO;
import br.com.thiaguten.richspring.model.Todo;

import java.util.List;

public interface TodoDAO extends BaseDAO<Todo, Long> {

    List<Todo> find(Todo todo);

}
