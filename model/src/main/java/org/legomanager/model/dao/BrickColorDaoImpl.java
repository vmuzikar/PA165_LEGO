package org.legomanager.model.dao;

import org.legomanager.model.entities.BrickColor;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Repository
@Transactional
public class BrickColorDaoImpl implements BrickColorDao {
    @PersistenceContext
    private EntityManager em;

    public BrickColor findById(long id) {
        return em.find(BrickColor.class, id);
    }

    public void create(BrickColor color) {
        em.persist(color);
    }

    public void delete(BrickColor color) {
        em.remove(color);
    }

    public void update(BrickColor color) {
        em.merge(color);
    }

    public List<BrickColor> findAll() {
        return em.createQuery("select c from BrickColor c", BrickColor.class).getResultList();
    }
}
