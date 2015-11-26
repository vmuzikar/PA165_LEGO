package service.services;

import org.legomanager.persistence.entities.Category;
import org.legomanager.service.services.AbstractBaseDaoService;
import org.legomanager.service.services.CategoryService;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests the service for manipulation with kits
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class CategoryServiceTest extends AbstractBaseDaoServiceTest<Category> {
    @Autowired
    @InjectMocks
    private CategoryService categoryService;

    @Override
    protected AbstractBaseDaoService<Category> getDaoService() {
        return categoryService;
    }

    @Override
    protected Category createEntity() {
        return new Category();
    }

    @Override
    protected void setIdForEntity(Category entity, long id) {
        entity.setId(id);
    }
}
