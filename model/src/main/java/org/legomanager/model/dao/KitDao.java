package org.legomanager.model.dao;

import org.legomanager.model.entities.Kit;

import java.util.List;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface KitDao {
    Kit findById(long id);
    void create(Kit kit);
    void delete(Kit kit);
    void update(Kit kit);
    List<Kit> findAll();
}
