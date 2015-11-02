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

    public void create(Brick color) {
        em.persist(color);
    }

    public void delete(Brick color) {
        em.remove(color);
    }

    public void update(Brick color) {
        em.merge(color);
    }

    public List<Brick> findAll() {
        return em.createQuery("select c from Brick c", Brick.class).getResultList();
    }
}
