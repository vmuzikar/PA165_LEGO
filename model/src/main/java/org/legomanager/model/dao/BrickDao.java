package org.legomanager.model.dao;

import org.legomanager.model.entities.Brick;

import java.util.List;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface BrickDao {
    Brick findById(long id);
    void create(Brick brick);
    void delete(Brick brick);
    void update(Brick brick);
    List<Brick> findAll();
}
