package org.legomanager.model.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.legomanager.model.entities.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author Michal Valeš <michal@vales.me>
 */
@Transactional
@Repository
public class CategoryDaoImpl implements CategoryDao {
    @PersistenceContext
    private EntityManager em;

    public Category findById(long id) {
        return em.find(Category.class, id);
    }

    public void create(Category category) {
        em.persist(category);
    }

    public void delete(Category category) {
        em.remove(em.merge(category));
    }

    public void update(Category category) {
        em.merge(category);
    }

    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Set.class).getResultList();
    }
}
