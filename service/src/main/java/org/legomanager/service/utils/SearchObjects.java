package org.legomanager.service.utils;

import java.util.Collections;
import java.util.Comparator;
import org.legomanager.persistence.dao.AbstractBaseDao;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import org.legomanager.persistence.entities.Kit;
import org.springframework.dao.DataRetrievalFailureException;

/**
 * Utility clas for generalized objects look-up.
 *
 * @author Michal Vale� <michal@vales.me>
 */
public abstract class SearchObjects<E> {
    
    private AbstractBaseDao<E> dao;

    public SearchObjects(AbstractBaseDao<E> dao) {
        this.dao = dao;
    }
    
    protected abstract Set<Kit> getKits(E entity);
    
    public List<E> getUnused() {
        List<E> fetched = null;
        try {
            fetched = dao.findAll();
        }
        catch (Exception e) {
            throw new DataRetrievalFailureException("There was an error on DAO layer");
        }
        Iterator<E> it = fetched.iterator();
        while (it.hasNext()) {
            E e = it.next();
            Set<Kit> kits = getKits(e);
            if (getKits(e).isEmpty()) {
            } else {
                it.remove();
            }
        }
        return fetched;
    }
    
    public List<E> getMostUsed(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("Count must be at least 1");
        }
        List<E> fetched = null;
        try {
            fetched = dao.findAll();
        }
        catch (Exception e) {
            throw new DataRetrievalFailureException("There was an error on DAO layer");
        }
        Collections.sort(fetched, new ComparatorByKitsSize());
        List<E> result = fetched.subList(0, Math.min(fetched.size(), count));
        return result;
    }
    
    private class ComparatorByKitsSize implements Comparator<E> {
        public int compare(E e1, E e2) {
            int e1S = getKits(e1).size();
            int e2S = getKits(e2).size();
            int r = (-1)*(e1S - e2S);
            return r;
        }
    }
    
}
