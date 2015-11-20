package org.legomanager;

import org.legomanager.model.dao.BrickDao;
import org.legomanager.model.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

/**
 * @author Michal Valeš <michal@vales.me>
 */
public class BrickTest extends AbstractTest {
    @Autowired
    private BrickDao brickDao;

    @Test
    public void CRUDTest() {
        
        Brick b1 = createBrick();

        // Create
        brickDao.create(b1);
        
        // Find
        Brick b2 = brickDao.findById(b1.getId());
        assertNotNull(b2);
        assertEquals(b2.getName(), b1.getName());
        
        // Find all
        assertEquals(brickDao.findAll().size(), 1);

        // Update
        b2.setName("updated");
        brickDao.update(b2);
        Brick b3 = brickDao.findById(b2.getId());
        assertNotNull(b3);
        assertEquals(b3.getName(), b2.getName());

        // Delete
        brickDao.delete(b3);
        assertNull(brickDao.findById(b3.getId()));
        assertEquals(brickDao.findAll().size(), 0);
    }
}
