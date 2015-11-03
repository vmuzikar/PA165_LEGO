package org.legomanager;

import org.legomanager.model.dao.CategoryDao;
import org.legomanager.model.entities.Category;
import org.legomanager.model.entities.Kit;
import org.legomanager.model.entities.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class CategoryTest extends AbstractTest {
    @Autowired
    private CategoryDao categoryDao;

    @Test
    public void CRUDTest() {
        Category category1 = createCategory();

        Kit kit1 = createKit(category1);
        kit1.setCategory(category1);

        Set set1 = createSet();
        set1.addCategory(category1);

        // Create/find
        categoryDao.create(category1);
        Category category2 = categoryDao.findById(category1.getId());
        assertNotNull(category2);
        assertEquals(category1.getName(), category2.getName());
        assertEquals(category1.getSets(), category1.getSets());
        assertEquals(category1.getKits(), category1.getKits());
        assertEquals(categoryDao.findAll().size(), 1);

        // Update
        category2.setName("Updated name");
        categoryDao.update(category2);
        Category category3 = categoryDao.findById(category2.getId());
        assertNotNull(category3);
        assertEquals(category3.getName(), category2.getName());

        // Delete
        categoryDao.delete(category3);
        assertNull(categoryDao.findById(category3.getId()));
        assertEquals(categoryDao.findAll().size(), 0);
    }
}
