package org.legomanager;

import org.legomanager.model.dao.BrickColorDao;
import org.legomanager.model.dao.BrickDao;
import org.legomanager.model.entities.Brick;
import org.legomanager.model.entities.BrickColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import java.awt.*;
import org.testng.Assert;

/**
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@ContextConfiguration("classpath:META-INF/spring-config.xml")
public class CRUD extends AbstractTestNGSpringContextTests {
    @Autowired
    private BrickColorDao brickColorDao;

    @Autowired
    private BrickDao brickDao;

    @PersistenceUnit
    private EntityManagerFactory entityManagerFactory;

    @Test
    public void brickColorTest() {
        BrickColor brickColor1 = new BrickColor();
        brickColor1.setColor(Color.RED);

        // Create/find test
        brickColorDao.create(brickColor1);
        BrickColor brickColor2 = brickColorDao.findById(brickColor1.getId());
        Assert.assertNotNull(brickColor2);
        Assert.assertEquals(brickColor2.getId(), brickColor1.getId());
        Assert.assertEquals(brickColor2.getColor(), brickColor1.getColor());

        // Update test
        brickColor2.setColor(Color.BLACK);
        brickColorDao.update(brickColor2);
        BrickColor brickColor3 = brickColorDao.findById(brickColor2.getId());
        Assert.assertNotNull(brickColor3);
        Assert.assertEquals(brickColor3.getColor(), brickColor2.getColor());
        Assert.assertNotEquals(brickColor3.getColor(), brickColor1.getColor());

        // Delete test
        brickColorDao.delete(brickColor3);
        BrickColor brickColor4 = brickColorDao.findById(brickColor3.getId());
        Assert.assertNull(brickColor4);
    }
}
