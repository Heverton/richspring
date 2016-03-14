package br.com.thiaguten.richspring.model;

import br.com.thiaguten.persistence.Versionable;
import br.com.thiaguten.persistence.entity.BaseEntity;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "TODO")
public class Todo extends BaseEntity<Long> implements Versionable {

    private static final long serialVersionUID = -7004205764576627787L;

    //    @Transient
    @Version
    @Column(name = "VERSION")
    private long version;

    @Id
    @Column(name = "ID", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "RESUME", length = 155, nullable = false)
    private String resume;

    @NotNull
    @NotBlank
    @Column(name = "DESCRIPTION", length = 4000, nullable = false)
    private String description;

    @Transient
    private boolean selected = false;

    public Todo() {
        super();
    }

    public Todo(String resume, String description) {
        super();
        this.resume = resume;
        this.description = description;
    }

    @Override
    public long getVersion() {
        return version;
    }

    public void setVersion(long version) {
        this.version = version;
    }

    @Override
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public boolean getSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    @Override
    public String toString() {
        return "Todo [version=" + version + ", id=" + id + ", resume=" + resume
                + ", description=" + description + "]";
    }

}
