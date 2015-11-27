package org.legomanager.persistence;

import org.legomanager.persistence.dao.AbstractBaseDao;
import org.legomanager.persistence.dao.CategoryDao;
import org.legomanager.persistence.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests for Category DAO
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class CategoryDaoTest extends AbstractBaseDaoTest<Category> {
    @Autowired
    private CategoryDao categoryDao;

    @Override
    protected AbstractBaseDao<Category> getDao() {
        return categoryDao;
    }

    @Override
    protected Category createEntity() {
        return createCategory();
    }

    @Override
    protected long getIdFromEntity(Category entity) {
        return entity.getId();
    }

    @Override
    protected void setSomeValueForEntity(Category entity) {
        entity.setName(entity.getName() + "New");
    }
}
