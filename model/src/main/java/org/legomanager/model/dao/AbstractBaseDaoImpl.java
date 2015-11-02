package org.legomanager.model.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public abstract class AbstractBaseDaoImpl<T> implements AbstractBaseDao<T> {
    protected Class<T> entityClass;

    @PersistenceContext
    private EntityManager em;

    public T findById(long id) {
        return em.find(entityClass, id);
    }

    public void create(T color) {
        em.persist(color);
    }

    public void delete(T color) {
        em.remove(color);
    }

    public void update(T color) {
        em.merge(color);
    }

    public List<T> findAll() {
        return em.createQuery("select e from " + entityClass.getSimpleName() + " e", entityClass).getResultList();
    }
}
