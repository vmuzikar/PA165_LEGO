package org.legomanager.model.dao;

import org.legomanager.model.entities.Kit;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Transactional
@Repository
public class KitDaoImpl implements KitDao {
    @PersistenceContext
    private EntityManager em;

    public Kit findById(long id) {
        return em.find(Kit.class, id);
    }

    public void create(Kit color) {
        em.persist(color);
    }

    public void delete(Kit color) {
        em.remove(color);
    }

    public void update(Kit color) {
        em.merge(color);
    }

    public List<Kit> findAll() {
        return em.createQuery("select c from Kit c", Kit.class).getResultList();
    }
}
