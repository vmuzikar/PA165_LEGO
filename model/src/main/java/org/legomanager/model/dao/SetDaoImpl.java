package org.legomanager.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.legomanager.model.entities.Set;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is base implementation of {@link SetDao}.
 * 
 * @author Michal Valeš <michal@vales.me>
 */
@Transactional
@Repository
public class SetDaoImpl implements SetDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Set findById(long id) {
        return em.find(Set.class, id);
    }

    @Override
    public void create(Set set) {
        em.persist(set);
    }

    @Override
    public void delete(Set set) {
        em.remove(em.merge(set));
    }

    @Override
    public void update(Set set) {
        em.merge(set);
    }

    @Override
    public List<Set> findAll() {
        return em.createQuery("select c from Set c", Set.class).getResultList();
    }
}
