package br.com.thiaguten.richspring.persistence;

import br.com.thiaguten.persistence.spi.provider.hibernate.HibernateJpaPersistenceProvider;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class HibernateJpaPersistenceProviderImpl extends HibernateJpaPersistenceProvider {

    private EntityManager entityManager;

    public EntityManager getEntityManager() {
        return entityManager;
    }

    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        if (entityManager == null) {
            throw new IllegalArgumentException("EntityManager must not be null");
        }
        this.entityManager = entityManager;
    }

}
