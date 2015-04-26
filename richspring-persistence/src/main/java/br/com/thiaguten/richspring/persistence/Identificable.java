package br.com.thiaguten.richspring.persistence;

import java.io.Serializable;

/**
 * Defines a class as identifiable by a primary key.
 *
 * @param <PK> primary key
 * @author Thiago Gutenberg
 */
public interface Identificable<PK extends Serializable> {

    /**
     * Get a primary key
     *
     * @return primary key
     */
    PK getId();

}
