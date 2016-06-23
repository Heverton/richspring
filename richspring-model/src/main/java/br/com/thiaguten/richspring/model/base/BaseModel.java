package br.com.thiaguten.richspring.model.base;

import br.com.thiaguten.persistence.core.Persistable;

import java.io.Serializable;

/**
 * Abstract Base Model class that provide convenient methods and can be
 * extended by Entities classes.
 *
 * @param <ID> the type of the identifier
 * @author Thiago Gutenberg Carvalho da Costa
 */
public abstract class BaseModel<ID extends Serializable> implements Persistable<ID> {

    private static final long serialVersionUID = -3489685468911329523L;

    /**
     * Checks if this instance has a valid id.
     *
     * @return true if this instance has id, otherwise false.
     */
    public boolean hasID() {
        return this.getId() != null;
    }

    /**
     * {@inheritDoc} Overridden to implements the method behavior.
     */
    @Override
    public String toString() {
        return getClass().getSimpleName() + "{id=" + getId() + "}";
    }

    /**
     * {@inheritDoc} Overridden to implements the method behavior.
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    /**
     * {@inheritDoc} Overridden to implements the method behavior.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BaseModel other = (BaseModel) obj;
        if (getId() == null)
            if (other.getId() != null)
                return false;
            else if (!getId().equals(other.getId()))
                return false;
        return true;
    }

}
