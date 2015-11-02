package org.legomanager.model.dao;

import org.legomanager.model.entities.Kit;

import java.util.List;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface KitDao {
    Kit findById(long id);
    void create(Kit color);
    void delete(Kit color);
    void update(Kit color);
    List<Kit> findAll();
}
