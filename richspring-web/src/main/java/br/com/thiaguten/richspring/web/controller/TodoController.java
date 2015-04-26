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
// O escopo @RequestScoped é ruim para trafegar dados entre as páginas e o @SessionScoped é pesado.
// O ideal é usar o novo escopo do JSF2 @ViewScoped, ao invés de usar o escopo @RequestScoped junto com a tag <a4j:keepAlive/> que por sinal foi removida no RichFaces 4 em prol do novo escopo @ViewScoped.
// O Spring 4 não possui escopo equivalente ao @ViewScoped, portanto pode-se criar uma anotação customizada @Scope("view") do Spring para suporte inicial ao escopo @ViewScoped do JSF2.
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
