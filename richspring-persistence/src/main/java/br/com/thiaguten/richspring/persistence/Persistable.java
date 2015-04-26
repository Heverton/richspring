package br.com.thiaguten.richspring.persistence;

import java.io.Serializable;

/**
 * Defines a class as persistable.
 *
 * @param <PK> primary key
 */
public interface Persistable<PK extends Serializable> extends Identificable<PK>, Serializable {

}
