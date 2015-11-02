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

        brickColorDao.create(brickColor1);

        BrickColor brickColor2 = brickColorDao.findById(brickColor1.getId());
        Assert.assertNotNull(brickColor2);
        Assert.assertEquals(brickColor2.getId(), brickColor1.getId());
        Assert.assertEquals(brickColor2.getColor(), brickColor1.getColor());
    }
}
