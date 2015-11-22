package org.legomanager.persistence.dao;

import java.util.List;

/**
 * Interface for the generalized DAO implementation
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface AbstractBaseDao<E> {
    E findById(long id);
    void create(E entity);
    void delete(E entity);
    void update(E entity);
    List<E> findAll();
}
