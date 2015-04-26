package br.com.thiaguten.richspring.core.service;

import br.com.thiaguten.richspring.model.Todo;

import java.util.List;

public interface TodoService {

    Todo saveOrUpdate(Todo todo);

    void delete(Todo todo);

    void deleteById(Long id);

    Todo get(Long id);

    List<Todo> list();

    List<Todo> find(Todo todo);

}
