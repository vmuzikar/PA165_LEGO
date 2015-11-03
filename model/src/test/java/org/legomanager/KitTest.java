package org.legomanager;

import org.legomanager.model.dao.KitDao;
import org.legomanager.model.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Michal Valeš <michal@vales.me>
 */
public class KitTest extends AbstractTest {
    @Autowired
    private KitDao kitDao;

    @Test
    public void CRUDTest() {
        
        Category c = createCategory();
        Kit k1 = createKit(c);

        // Create
        kitDao.create(k1);
        
        // Find
        Kit k2 = kitDao.findById(k1.getId());
        assertNotNull(k2);
        assertEquals(k2.getName(), k1.getName());
        assertEquals(k2.getCategory(), k1.getCategory());
        assertEquals(k2.getBricks(), k1.getBricks());
        assertEquals(k2.getPrice(), k1.getPrice());
        assertEquals(k2.getCurrency(), k1.getCurrency());
        assertEquals(k2.getMaxAge(), k1.getMaxAge());
        assertEquals(k2.getMinAge(), k1.getMinAge());
        assertEquals(k2.getSets(), k1.getSets());
        
        // Find all
        assertEquals(kitDao.findAll().size(), 1);

        // Update
        k2.setName("updated");
        kitDao.update(k2);
        Kit k3 = kitDao.findById(k2.getId());
        assertNotNull(k3);
        assertEquals(k3.getName(), k2.getName());

        // Delete
        kitDao.delete(k3);
        assertNull(kitDao.findById(k3.getId()));
        assertEquals(kitDao.findAll().size(), 0);
    }
}
