package org.legomanager.persistence.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Base implementation of CRUD operations
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public abstract class AbstractBaseDaoImpl<E> implements AbstractBaseDao<E> {
    @PersistenceContext
    protected EntityManager em;

    private Class<E> entityClass;

    protected AbstractBaseDaoImpl(Class<E> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public E findById(long id) {
        return em.find(entityClass, id);
    }

    @Override
    public void create(E entity) {
        em.persist(entity);
    }

    @Override
    public void delete(E entity) {
        em.remove(entity);
    }

    @Override
    public void update(E entity) {
        em.merge(entity);
    }

    @Override
    public List<E> findAll() {
        return em.createQuery("select c from " + entityClass.getTypeName() + " c", entityClass).getResultList();
    }
}
