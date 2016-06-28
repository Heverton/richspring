package br.com.thiaguten.richspring.core.dao;

import br.com.thiaguten.richspring.core.service.TodoService;
import br.com.thiaguten.richspring.core.spring.config.RootConfig;
import br.com.thiaguten.richspring.model.Todo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class TodoTest {

    @Autowired
    private TodoService todoService;

    @Test
    public void crudTest() {
        // create
        Todo todo = new Todo("resume", "description");
        assertNull(todo.getId());
        Todo todoSaved = todoService.saveOrUpdate(todo);

        // read
        assertNotNull(todoSaved.getId());
        Todo todoReturn = todoService.get(todoSaved.getId());
        assertEquals(todoReturn, todoSaved);

        // find
        assertNotNull(todoReturn);
        List<Todo> todoFind = todoService.find(todoReturn);
        assertEquals(todoFind.get(0), todoReturn);

        // update
        todoReturn.setDescription("description2");
        assertNotNull(todoReturn.getId());
        Todo todoUpdated = todoService.saveOrUpdate(todoReturn);
        assertEquals(todoUpdated, todoReturn);

        // list
        List<Todo> todoList = todoService.list();
        assertEquals(1, todoList.size());
        assertEquals(todoList.get(0), todoUpdated);

        // delete
        assertNotNull(todoUpdated.getId());
        todoService.deleteById(todoUpdated.getId());
        Todo todoDeleted = todoService.get(todoUpdated.getId());
        assertNull(todoDeleted);

        // find
        assertNull(todoDeleted);
        List<Todo> todoFindNull = todoService.find(todoDeleted);
        assertEquals(todoFindNull, Collections.emptyList());
    }

}
