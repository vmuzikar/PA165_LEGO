package org.legomanager;

import org.legomanager.model.dao.SetDao;
import org.legomanager.model.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class SetTest extends AbstractTest {
    @Autowired
    private SetDao setDao;

    @Test
    public void CRUDTest() {
        Category category = createCategory();
        Kit kit1 = createKit(category);
        kit1.setMinAge((short) 1);

        Kit kit2 = createKit(category);
        kit2.setMaxAge((short) 99);

        Set set1 = createSet();
        set1.addKit(kit1);
        set1.addKit(kit2);
        set1.addCategory(category);

        // Create/find
        setDao.create(set1);
        Set set2 = setDao.findById(set1.getId());
        assertNotNull(set2);
        assertEquals(set2.getName(), set1.getName());
        assertEquals(set2.getCategories(), set1.getCategories());
        assertEquals(set2.getKits(), set1.getKits());
        assertEquals(set2.getPrice(), set1.getPrice());
        assertEquals(set2.getCurrency(), set1.getCurrency());
        assertEquals(set2.getMinAge(), set1.getMinAge());
        assertEquals(set2.getMaxAge(), set1.getMaxAge());
        assertEquals(setDao.findAll().size(), 1);

        // Update
        set2.setName("Updated name");
        setDao.update(set2);
        Set set3 = setDao.findById(set2.getId());
        assertNotNull(set3);
        assertEquals(set3.getName(), set2.getName());

        // Delete
        setDao.delete(set3);
        assertNull(setDao.findById(set3.getId()));
        assertEquals(setDao.findAll().size(), 0);
    }
}
