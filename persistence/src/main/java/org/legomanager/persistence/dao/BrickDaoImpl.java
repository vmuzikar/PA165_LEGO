package org.legomanager.persistence.dao;

import org.legomanager.persistence.entities.Brick;
import org.springframework.stereotype.Repository;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Repository
public class BrickDaoImpl extends AbstractBaseDaoImpl<Brick> implements BrickDao {
    public BrickDaoImpl() {
        super(Brick.class);
    }
}
