package org.legomanager.model.dao;

import java.util.List;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface AbstractBaseDao<T> {
    T findById(long id);
    void create(T color);
    void delete(T color);
    void update(T color);
    List<T> findAll();
}