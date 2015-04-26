package br.com.thiaguten.richspring.persistence;

/**
 * Defines a class as versionable by a version number.
 *
 * @author Thiago Gutenberg
 */
public interface Versionable {

    /**
     * Get a version
     *
     * @return version
     */
    long getVersion();

}
