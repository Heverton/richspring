package br.com.thiaguten.richspring.web.component.filter;

import br.com.thiaguten.richspring.model.Todo;

import javax.inject.Named;

@Named("todoFilter")
public class TodoFilter implements Filter<Todo> {

    private String resume;

    private boolean matchResume(String resume) {
        return this.resume == null || resume.startsWith(this.resume);
    }

    @Override
    public boolean match(Todo todo) {
        return todo != null && matchResume(todo.getResume());
    }

    // getters and setters

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }
}
