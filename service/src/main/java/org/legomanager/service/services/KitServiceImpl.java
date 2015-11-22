package org.legomanager.service.services;

import org.legomanager.persistence.dao.KitDao;
import org.legomanager.persistence.entities.Kit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for manipulation with kits
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Service
public class KitServiceImpl extends AbstractBaseDaoServiceImpl<Kit> implements KitService {
    @Autowired
    public KitServiceImpl(KitDao dao) {
        super(dao, Kit.class);
    }

    public List<Kit> getKitsForKids() {
        KitDao kitDao = (KitDao) getDao();
        return kitDao.getKitsWithAgeRange((short) 5, (short) 10);
    }

    public List<Kit> getKitsForTeenage() {
        KitDao kitDao = (KitDao) getDao();
        return kitDao.getKitsWithAgeRange((short) 10, (short) 18);
    }

    public List<Kit> getKitsForAdults() {
        KitDao kitDao = (KitDao) getDao();
        return kitDao.getKitsWithAgeRange((short) 18, (short) 99);
    }
}
