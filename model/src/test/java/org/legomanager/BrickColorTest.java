package org.legomanager;

import org.legomanager.model.dao.BrickColorDao;
import org.legomanager.model.entities.BrickColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;
import java.awt.*;

import static org.testng.Assert.*;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class BrickColorTest extends AbstractTest {
    @Autowired
    private BrickColorDao brickColorDao;

    @Test
    public void CRUDTest() {
        BrickColor brickColor1 = createBrickColor();

        // Create/find test
        brickColorDao.create(brickColor1);
        BrickColor brickColor2 = brickColorDao.findById(brickColor1.getId());
        assertNotNull(brickColor2);
        assertEquals(brickColor2.getColor(), brickColor1.getColor());
        assertEquals(brickColorDao.findAll().size(), 1);

        // Update test
        brickColor2.setColor(Color.BLACK);
        brickColorDao.update(brickColor2);
        BrickColor brickColor3 = brickColorDao.findById(brickColor2.getId());
        assertNotNull(brickColor3);
        assertEquals(brickColor3.getColor(), brickColor2.getColor());

        // Delete test
        brickColorDao.delete(brickColor3);
        BrickColor brickColor4 = brickColorDao.findById(brickColor3.getId());
        assertNull(brickColor4);
        assertEquals(brickColorDao.findAll().size(), 0);
    }
}
