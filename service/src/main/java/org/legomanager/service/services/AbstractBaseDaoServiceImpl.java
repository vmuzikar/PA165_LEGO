package org.legomanager.service.services;

import org.legomanager.persistence.dao.AbstractBaseDao;

import java.util.List;

/**
 * Base implementation of service that uses DAO with base CRUD operations
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public abstract class AbstractBaseDaoServiceImpl<E> implements AbstractBaseDaoService<E> {
    private AbstractBaseDao<E> dao;
    private Class<E> entityClass;

    protected AbstractBaseDaoServiceImpl(AbstractBaseDao<E> dao, Class<E> entityClass) {
        this.dao = dao;
        this.entityClass = entityClass;
    }

    protected AbstractBaseDao<E> getDao() {
        if (dao == null) {
            throw new IllegalStateException("DAO is null.");
        }

        return dao;
    }

    public E findById(long id) {
        return getDao().findById(id);
    }

    public List<E> findAll() {
        return getDao().findAll();
    }

    public void create(E entity) {
        getDao().create(entity);
    }

    public void remove(E entity) {
        getDao().delete(entity);
    }
}
