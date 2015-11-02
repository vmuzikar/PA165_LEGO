package org.legomanager.model.dao;

import org.legomanager.model.entities.BrickColor;
import java.util.List;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface BrickColorDao {
    BrickColor findById(long id);
    void create(BrickColor color);
    void delete(BrickColor color);
    void update(BrickColor color);
    List<BrickColor> findAll();
}
