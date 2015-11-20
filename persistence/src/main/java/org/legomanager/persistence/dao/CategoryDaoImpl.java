package org.legomanager.persistence.dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.legomanager.persistence.entities.Category;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * This is base implementation of {@link CategoryDao}.
 * 
 * @author Michal Valeš <michal@vales.me>
 */
@Repository
public class CategoryDaoImpl implements CategoryDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Category findById(long id) {
        return em.find(Category.class, id);
    }

    @Override
    public void create(Category category) {
        em.persist(category);
    }

    @Override
    public void delete(Category category) {
        em.remove(em.merge(category));
    }

    @Override
    public void update(Category category) {
        em.merge(category);
    }

    @Override
    public List<Category> findAll() {
        return em.createQuery("select c from Category c", Category.class).getResultList();
    }
}
