package org.legomanager.service.services;

import org.legomanager.persistence.dao.BrickDao;
import org.legomanager.persistence.entities.Brick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import org.legomanager.persistence.dao.AbstractBaseDao;
import org.legomanager.persistence.entities.Kit;
import org.legomanager.service.utils.SearchObjects;

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
        SearchBrick search = new SearchBrick(getDao());
        return search.getUnused();
    }

    public List<Brick> getMostUsed(int count) {
        SearchBrick search = new SearchBrick(getDao());
        return search.getMostUsed(count);
    }
    
    private class SearchBrick extends SearchObjects<Brick> {

        public SearchBrick(AbstractBaseDao<Brick> dao) {
            super(dao);
        }
        
        @Override
        protected Set<Kit> getKits(Brick entity) {
            return entity.getKits();
        }
        
    }
}
