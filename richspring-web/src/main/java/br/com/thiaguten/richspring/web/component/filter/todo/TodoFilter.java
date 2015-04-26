package br.com.thiaguten.richspring.web.component.filter.todo;

import br.com.thiaguten.richspring.model.Todo;
import br.com.thiaguten.richspring.web.component.filter.Filter;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("request")
public class TodoFilter implements Filter<Todo> {

    private String resume;
    private String description;

    private boolean matchResume(String resume) {
        return this.resume == null || this.resume.equalsIgnoreCase(resume);
    }

    private boolean matchDescription(String description) {
        return this.description == null || this.description.equalsIgnoreCase(description);
    }

    @Override
    public boolean match(Todo todo) {
        return todo != null && matchResume(todo.getResume()) && matchDescription(todo.getDescription());
    }

    // getters and setters

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
