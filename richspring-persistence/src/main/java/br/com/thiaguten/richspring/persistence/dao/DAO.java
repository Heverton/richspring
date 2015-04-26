package br.com.thiaguten.richspring.persistence.dao;

import br.com.thiaguten.richspring.persistence.Persistable;

import java.io.Serializable;
import java.util.List;

/**
 * Generic DAO
 *
 * @param <T>  entityClazz
 * @param <PK> primaryKey
 * @author Thiago Gutenberg
 */
public interface DAO<T extends Persistable<? extends Serializable>, PK extends Serializable> {

    Class<T> getEntityClazz();

    Class<PK> getPrimaryKeyClazz();

    T save(T t);

    T get(PK pk);

    T update(T t);

    void delete(T t);

    void deleteById(PK pk);

    List<T> list();

}
