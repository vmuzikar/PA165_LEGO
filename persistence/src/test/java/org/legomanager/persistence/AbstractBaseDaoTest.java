package org.legomanager.persistence;

import org.legomanager.persistence.dao.AbstractBaseDao;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Base test class for testing DAOs with base CRUD operations
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Rollback(true)
@ContextConfiguration("classpath:META-INF/persistence-context.xml")
public abstract class AbstractBaseDaoTest<E> extends AbstractTest {

    protected abstract AbstractBaseDao<E> getDao();

    /**
     * Creates a *unique* entity, e.g. always sets a unique name
     *
     * @return entity
     */
    protected abstract E createEntity();

    protected abstract long getIdFromEntity(E entity);

    /**
     * For update tests
     *
     * @param entity
     */
    protected abstract void setSomeValueForEntity(E entity);

    @Test
    public void findByIdTest() {
        E entity = createEntity();
        getDao().create(entity);

        E foundEntity = getDao().findById(getIdFromEntity(entity));
        Assert.assertEquals(foundEntity, entity);
    }

    @Test
    public void createTest() {
        E entity = createEntity();
        getDao().create(entity);

        Assert.assertNotEquals(getIdFromEntity(entity), 0L);
    }

    @Test
    public void findAllTest() {
        List<E> entities = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            E entity = createEntity();
            entities.add(entity);
            getDao().create(entity);
        }

        List<E> foundEntities = getDao().findAll();
        Assert.assertEquals(foundEntities, entities);
    }

    @Test
    public void deleteTest() {
        E entity = createEntity();
        getDao().create(entity);
        getDao().create(createEntity());

        getDao().delete(entity);

        Assert.assertNull(getDao().findById(getIdFromEntity(entity)));
    }

    @Test
    public void updateTest() {
        E entity = createEntity();
        getDao().create(entity);

        setSomeValueForEntity(entity);
        getDao().update(entity);

        E foundEntity = getDao().findById(getIdFromEntity(entity));
        Assert.assertEquals(foundEntity, entity);
    }
}
