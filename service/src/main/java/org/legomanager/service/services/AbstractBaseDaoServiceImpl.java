package org.legomanager.service.services;

import org.legomanager.persistence.dao.AbstractBaseDao;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.dao.NonTransientDataAccessResourceException;

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
        try {
            return getDao().findById(id);
        }
        catch (Exception e) {
            throw new DataRetrievalFailureException("There was an error on DAO layer");
        }
    }

    public List<E> findAll() {
        try {
            return getDao().findAll();
        }
        catch (Exception e) {
            throw new DataRetrievalFailureException("There was an error on DAO layer");
        }
    }

    public void create(E entity) {
        try {
            getDao().create(entity);
        }
        catch (Exception e) {
            throw new NonTransientDataAccessResourceException("There was an error on DAO layer");
        }
    }

    public void remove(E entity) {
        try {
            getDao().delete(entity);
        }
        catch (Exception e) {
            throw new NonTransientDataAccessResourceException("There was an error on DAO layer");
        }
    }
}
