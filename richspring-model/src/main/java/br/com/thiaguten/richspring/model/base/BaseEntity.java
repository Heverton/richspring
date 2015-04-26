package br.com.thiaguten.richspring.model.base;

import br.com.thiaguten.richspring.persistence.Persistable;
import br.com.thiaguten.richspring.persistence.Versionable;

public abstract class BaseEntity implements Persistable<Long>, Versionable {

    private static final long serialVersionUID = -7144455770395490840L;

    public boolean hasID() {
        return this.getId() != null;
    }

    @Override
    public String toString() {
        return getClass().getSimpleName() + "{" + "version=" + getVersion() + ", id=" + getId() + "}";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        BaseEntity other = (BaseEntity) obj;
        if (getId() == null) {
            if (other.getId() != null) {
                return false;
            }
        } else if (!getId().equals(other.getId())) {
            return false;
        }
        return true;
    }

}
