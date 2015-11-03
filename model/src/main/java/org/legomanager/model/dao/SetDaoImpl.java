package org.legomanager.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.legomanager.model.entities.Set;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Michal Valeš <michal@vales.me>
 */
@Transactional
@Repository
public class SetDaoImpl implements SetDao {
    @PersistenceContext
    private EntityManager em;

    public Set findById(long id) {
        return em.find(Set.class, id);
    }

    public void create(Set set) {
        em.persist(set);
    }

    public void delete(Set set) {
        em.remove(em.merge(set));
    }

    public void update(Set set) {
        em.merge(set);
    }

    public List<Set> findAll() {
        return em.createQuery("select c from Set c", Set.class).getResultList();
    }
}
