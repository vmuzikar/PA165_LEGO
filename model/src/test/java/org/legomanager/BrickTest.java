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
        b1.addColor(createBrickColor());
        b1.addColor(createBrickColor());
        b1.addColor(createBrickColor());

        // Create
        brickDao.create(b1);
        
        // Find
        Brick b2 = brickDao.findById(b1.getId());
        assertNotNull(b2);
        assertEquals(b2.getName(), b1.getName());
        assertEquals(b2.getColors(), b1.getColors());
        
        // Find all
        b2.addColor(createBrickColor());
        brickDao.create(b2);
        assertEquals(brickDao.findAll().size(), 2);

        /*
        // Update
        Brick b3 = b2;
        b2.setName("updated");
        brickDao.update(b2);
        Brick b4 = brickDao.findById(b2.getId());
        assertNotNull(b4);
        assertEquals(b4.getName(), b2.getName());
        assertNotEquals(b4.getName(), b3.getName());

        // Delete
        brickDao.delete(b4);
        assertNull(brickDao.findById(b4.getId()));
        assertEquals(brickDao.findAll().size(), 1);
        brickDao.delete(b1);
        assertNull(brickDao.findById(b1.getId()));
        assertEquals(brickDao.findAll().size(), 0);
                */
    }
}
