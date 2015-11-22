package org.legomanager.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Base implementation of CRUD operations
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public abstract class AbstractBaseDaoImpl<E> {
    @PersistenceContext
    private EntityManager em;

    private Class<E> entityClass;

    protected AbstractBaseDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    public E findById(long id) {
        return em.find(entityClass, id);
    }

    public void create(E entity) {
        em.persist(entity);
    }

    public void delete(E entity) {
        em.remove(em.merge(entity));
    }

    public void update(E entity) {
        em.merge(entity);
    }

    public List<E> findAll() {
        return em.createQuery("select c from " + entityClass.getTypeName() + " c", entityClass).getResultList();
    }
}
