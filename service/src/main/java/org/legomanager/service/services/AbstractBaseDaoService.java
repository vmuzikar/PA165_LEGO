package org.legomanager.service.services;

import java.util.List;

/**
 * Interface for base implementation of service that uses DAO with base CRUD operations
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface AbstractBaseDaoService<E> {
    E findById(long id);
    List<E> findAll();
    void create(E entity);
    void remove(E entity);
    void update(E entity);
}
