package br.com.thiaguten.richspring.web.controller;

import br.com.thiaguten.richspring.core.service.TodoService;
import br.com.thiaguten.richspring.model.Todo;
import org.springframework.beans.factory.annotation.Autowired;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
/**
 * The @RequestScoped scope is bad to traffic data between pages and the @SessionScoped is heavy.
 * Ideally, use the new scope of JSF2 @ViewScoped, instead of using the @RequestScoped scope with the <a4j:keepAlive/>
 * tag which by the way was removed in RichFaces 4 in favor of the new @ViewScoped scope.
 * The Spring 4 does not have equivalent scope to @ViewScoped, so you can create a custom annotation @Scope("view") of
 * Spring for initial support @ViewScoped the JSF2 scope.
 */
public class TodoController implements Serializable {

    private static final long serialVersionUID = -8729367596577540910L;

    private int page;
    private Todo todo;
    private List<Todo> todos;

    private final TodoService todoService;

    @Autowired
    public TodoController(TodoService todoService) {
        this.todoService = todoService;
    }

    @PostConstruct
    public void init() {
        this.page = 1;
        this.todo = new Todo();
        this.todos = todoService.list();
    }

    public String pageSearch() {
        return "/xhtmls/todo/search-list";
    }

    public String pagePersist() {
        return "/xhtmls/todo/create-edit";
    }

    public String prepareCreate() {
        this.todo = new Todo();
        return pagePersist();
    }

    public String prepareEdit(Todo todo) {
        this.todo = todo;
        return pagePersist();
    }

    public void search() {
        this.todos = todoService.find(todo);
    }

    public void list() {
        this.todos = todoService.list();
    }

    public String createOrEdit() {
        todoService.saveOrUpdate(todo);
        clear();
        return pageSearch();
    }

    public String remove() {
        todoService.delete(todo);
        clear();
        return pageSearch();
    }

    public void clear() {
        init();
    }

    // getters and setters

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
