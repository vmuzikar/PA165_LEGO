package org.legomanager.service.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.legomanager.persistence.dao.BrickDao;
import org.legomanager.persistence.entities.Brick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementation of service for manipulation with kits
 *
 * @author Michal Vale� <michal@vales.me>
 */
@Service
public class BrickServiceImpl extends AbstractBaseDaoServiceImpl<Brick> implements BrickService {
    @Autowired
    public BrickServiceImpl(BrickDao dao) {
        super(dao, Brick.class);
    }

    public List<Brick> getUnused() {
        BrickDao brickDao = (BrickDao) getDao();
        List<Brick> fetchedBricks = null;
        try {
            fetchedBricks = brickDao.findAll();
        }
        catch (Exception e) {
            throw new DataRetrievalFailureException("There was an error on DAO layer");
        }
        Iterator<Brick> it = fetchedBricks.iterator();
        while (it.hasNext()) {
            Brick b = it.next();
            if (b.getKits().isEmpty()) {
                it.remove();
            }
        }
        return fetchedBricks;
    }

    public List<Brick> getMostUsed(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("Count must be at least 1");
        }
        BrickDao brickDao = (BrickDao) getDao();
        List<Brick> fetchedBricks = null;
        try {
            fetchedBricks = brickDao.findAll();
        }
        catch (Exception e) {
            throw new DataRetrievalFailureException("There was an error on DAO layer");
        }
        Collections.sort(fetchedBricks, new BrickComparatorByKitsSize());
        List<Brick> result = fetchedBricks.subList(0, Math.min(fetchedBricks.size(), count));
        return result;
    }
    
    private class BrickComparatorByKitsSize implements Comparator<Brick> {
        public int compare(Brick b1, Brick b2) {
            return (-1) * (b2.getKits().size() - b1.getKits().size());
        }
    }
}
