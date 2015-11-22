package org.legomanager.persistence.dao;

import org.legomanager.persistence.entities.Kit;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Repository
public class KitDaoImpl extends AbstractBaseDaoImpl<Kit> implements KitDao {
    protected KitDaoImpl() {
        super(Kit.class);
    }

    @Override
    public List<Kit> getKitsWithAgeRange(short ageFrom, short ageTo) {
        TypedQuery<Kit> query = em.createQuery("SELECT k FROM Kit k WHERE k.minAge >= :minAge AND k.maxAge <= :maxAge", Kit.class);
        query.setParameter("maxAge", ageFrom);
        query.setParameter("minAge", ageTo);
        return query.getResultList();
    }
}
