package org.legomanager.model.dao;

import org.legomanager.model.entities.Brick;
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
public class BrickDaoImpl implements BrickDao {
    @PersistenceContext
    private EntityManager em;

    public Brick findById(long id) {
        return em.find(Brick.class, id);
    }

    public void create(Brick brick) {
        em.persist(brick);
    }

    public void delete(Brick brick) {
        em.remove(em.merge(brick));
    }

    public void update(Brick brick) {
        em.merge(brick);
    }

    public List<Brick> findAll() {
        return em.createQuery("select c from Brick c", Brick.class).getResultList();
    }
}
