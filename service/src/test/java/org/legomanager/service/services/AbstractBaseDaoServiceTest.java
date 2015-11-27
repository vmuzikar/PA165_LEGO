package org.legomanager.service.services;

import org.legomanager.persistence.dao.AbstractBaseDao;
import org.mockito.Mock;
import org.testng.Assert;
import org.testng.annotations.Test;
import static org.mockito.Mockito.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Base test class for testing services that use DAO with base CRUD operations
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public abstract class AbstractBaseDaoServiceTest<E> extends AbstractServiceTest {

    /**
     * Implements getter for the specific AbstractBaseDaoService
     *
     * @return AbstractBaseDaoService
     */
    protected abstract AbstractBaseDaoService<E> getDaoService();

    /**
     * Implements getter for the specific AbstractBaseDao
     *
     * @return base DAO
     */
    protected abstract AbstractBaseDao<E> getDao();

    /**
     * Subclass must implement creating (NOT persisting) of a specific entity
     *
     * @return an entity
     */
    protected abstract E createEntity();

    /**
     * Subclass must implement this method for setting the primary key of an entity
     *
     * @param entity
     * @param id
     */
    protected abstract void setIdForEntity(E entity, long id);

    @Test
    public void findByIdTest() {
        E entity = createEntity();
        long entityId = 1;
        setIdForEntity(entity, entityId);

        when(getDao().findById(1)).thenReturn(entity);

        E foundEntity = getDaoService().findById(entityId);
        Assert.assertEquals(foundEntity, entity);
    }

    @Test
    public void findAllTest() {
        List<E> entities = new ArrayList<E>();
        for (int i = 1; i < 10; i++) {
            E entity = createEntity();
            setIdForEntity(entity, i);
        }

        doReturn(entities).when(getDao()).findAll();

        List<E> foundEntities = getDaoService().findAll();
        Assert.assertEquals(foundEntities, entities);
    }

    @Test
    public void createTest() {
        E entity = createEntity();
        getDaoService().create(entity);

        verify(getDao()).create(entity);
    }

    @Test
    public void removeTest() {
        E entity = createEntity();
        setIdForEntity(entity, 1);
        getDaoService().remove(entity);
        verify(getDao()).delete(entity);
    }
}
