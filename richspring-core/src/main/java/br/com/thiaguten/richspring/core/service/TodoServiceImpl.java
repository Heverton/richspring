package br.com.thiaguten.richspring.core.service;

import br.com.thiaguten.richspring.core.dao.TodoDAO;
import br.com.thiaguten.richspring.core.util.JSFUtils;
import br.com.thiaguten.richspring.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("todoService")
@Transactional(readOnly = true)
public class TodoServiceImpl implements TodoService {

    private final TodoDAO todoDAO;

    @Autowired
    public TodoServiceImpl(TodoDAO todoDAO) {
        this.todoDAO = todoDAO;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Todo saveOrUpdate(Todo todo) {
        Todo todoReturn;
        if (todo.hasID()) {
            todoReturn = todoDAO.update(todo);
            JSFUtils.addUpdateInfoMessage();
        } else {
            todoReturn = todoDAO.create(todo);
            JSFUtils.addSaveInfoMessage();
        }
        return todoReturn;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void delete(Todo todo) {
        if (todo.hasID()) {
            todoDAO.delete(todo);
            JSFUtils.addDeleteInfoMessage();
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteAll(List<Todo> todos) {
        for (int i = 0; i < todos.size(); i++) {
            Todo todo = todos.get(i);
            if (todo.hasID()) {
                todoDAO.delete(todo);
                if (i == (todos.size() - 1)) {
                    JSFUtils.addDeleteAllInfoMessage();
                }
            }
        }
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(Long id) {
        if (id != null) {
            todoDAO.deleteById(id);
            JSFUtils.addDeleteInfoMessage();
        }
    }

    @Override
    public Todo get(Long id) {
        return todoDAO.read(id);
    }

    @Override
    public List<Todo> list() {
        return todoDAO.findAll();
    }

    @Override
    public List<Todo> find(Todo todo) {
        return todoDAO.find(todo);
    }

}
