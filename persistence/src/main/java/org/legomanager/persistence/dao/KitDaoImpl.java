package org.legomanager.persistence.dao;

import org.legomanager.persistence.entities.Kit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Repository
public class KitDaoImpl implements KitDao {
    @PersistenceContext
    private EntityManager em;

    public Kit findById(long id) {
        return em.find(Kit.class, id);
    }

    public void create(Kit kit) {
        em.persist(kit);
    }

    public void delete(Kit kit) {
        em.remove(em.merge(kit));
    }

    public void update(Kit kit) {
        em.merge(kit);
    }

    public List<Kit> findAll() {
        return em.createQuery("select c from Kit c", Kit.class).getResultList();
    }
}
