package br.com.thiaguten.richspring.persistence.dao;

import br.com.thiaguten.richspring.persistence.Persistable;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Projections;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO Implementation
 *
 * @param <T>  entityClazz
 * @param <PK> primaryKey
 * @author Thiago Gutenberg
 */
@SuppressWarnings("unchecked")
public abstract class AbstractDAO<T extends Persistable<? extends Serializable>, PK extends Serializable> implements DAO<T, PK> {

    private final Class<T> entityClazz;
    private final Class<PK> primaryKeyClazz;

    private EntityManager entityManager;

    public AbstractDAO() {
        java.lang.reflect.ParameterizedType genericSuperClass = (java.lang.reflect.ParameterizedType) getClass().getGenericSuperclass();
        this.entityClazz = (Class<T>) genericSuperClass.getActualTypeArguments()[0];
        this.primaryKeyClazz = (Class<PK>) genericSuperClass.getActualTypeArguments()[1];
    }

    // convenient methods, in case to use the hibernate criteria api

    protected final Session getSession() {
        return (Session) entityManager.getDelegate();
    }

    protected List<T> findByCriteria(List<Criterion> criterions) {
        Criteria criteria = getSession().createCriteria(entityClazz);
        if (criterions != null) {
            criterions.forEach(criteria::add);
        }
        return criteria.list();
    }

    protected long countByCriteria(List<Criterion> criterions) {
        Criteria criteria = getSession().createCriteria(entityClazz);
        criteria.setProjection(Projections.rowCount());
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        if (criterions != null) {
            criterions.forEach(criteria::add);
        }
        return ((Long) criteria.uniqueResult());
    }

    // override methods

    @Override
    public Class<T> getEntityClazz() {
        return entityClazz;
    }

    @Override
    public Class<PK> getPrimaryKeyClazz() {
        return primaryKeyClazz;
    }

    @Override
    public T save(T t) {
        if (t.getId() != null) {
            t = entityManager.merge(t);
        } else {
            entityManager.persist(t);
        }
        return t;
    }

    @Override
    public T get(PK pk) {
        return entityManager.find(entityClazz, pk);
    }

    @Override
    public T update(T t) {
        return save(t);
    }

    @Override
    public void delete(T t) {
        deleteByIdOrEntity(t, null);
    }

    @Override
    public void deleteById(PK pk) {
        deleteByIdOrEntity(null, pk);
    }

    @Override
    public List<T> list() {
        return getSession().createCriteria(entityClazz).list();
    }

    // private methods

    private void deleteByIdOrEntity(T t, PK pk) {
        if (pk == null && (t == null || t.getId() == null)) {
            throw new PersistenceException(
                    "Could not perform delete operation because the entity or ID is null.");
        }
        PK id = pk;
        if (id == null) {
            id = (PK) t.getId();
        }
        T entidade = getEntityManager().getReference(entityClazz, id);
        entityManager.remove(entidade);
    }

    // getters and setters

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

    @Override
    public String toString() {
        return "AbstractDAO [entityManager=" + entityManager + "]";
    }
}
