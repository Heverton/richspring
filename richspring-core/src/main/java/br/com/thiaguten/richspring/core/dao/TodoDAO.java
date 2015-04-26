package br.com.thiaguten.richspring.core.dao;

import br.com.thiaguten.richspring.model.Todo;
import br.com.thiaguten.richspring.persistence.dao.DAO;

import java.util.List;

public interface TodoDAO extends DAO<Todo, Long> {

    List<Todo> find(Todo todo);

}
