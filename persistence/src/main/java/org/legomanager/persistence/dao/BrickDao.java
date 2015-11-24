package org.legomanager.persistence.dao;

import org.legomanager.persistence.entities.Brick;

import java.util.List;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface BrickDao extends AbstractBaseDao<Brick> {
    public Brick getLargestBrick(int maxSize1, int maxSize2);
}
