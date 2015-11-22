package org.legomanager.persistence.dao;

import org.legomanager.persistence.entities.Kit;
import org.springframework.stereotype.Repository;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Repository
public class KitDaoImpl extends AbstractBaseDaoImpl<Kit> implements KitDao {
    protected KitDaoImpl() {
        super(Kit.class);
    }
}
