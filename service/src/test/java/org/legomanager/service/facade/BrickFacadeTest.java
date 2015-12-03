package org.legomanager.service.facade;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.legomanager.api.dto.BrickDto;
import org.legomanager.api.dto.CategoryDto;
import org.legomanager.api.dto.KitDto;
import org.legomanager.api.facade.BrickFacade;
import org.legomanager.api.facade.KitFacade;
import org.legomanager.persistence.entities.Brick;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests the service for brick facade
 *
 * @author Michal Vale� <michal@vales.me>
 */
@ContextConfiguration("classpath:META-INF/service-context.xml")
@Rollback(true)
public class BrickFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Autowired
    private BrickFacade brickFacade;

    @Autowired
    private KitFacade kitFacade;
    
    private BrickDto o1, o2;
    private KitDto kit;
    
    @BeforeMethod
    public void initObjects() {
        o1 = new BrickDto();
        o1.setName("Brick 1");
        o1.setWidth(1);
        o1.setHeight(1);

        Set<BrickDto> bricks = new HashSet<BrickDto>();
        bricks.add(o1);

        CategoryDto category = new CategoryDto();
        category.setName("Categ");

        kit = new KitDto();
        kit.setName("Kit");
        kit.setCategory(category);
        kit.setMinAge((short) 1);
        kit.setMaxAge((short) 9);
        kit.setCurrency(Currency.getInstance("CZK"));
        kit.setPrice(new BigDecimal("2459.99"));
        kit.setBricks(bricks);
        
        o2 = new BrickDto();
        o2.setName("Brick 2");
        o2.setWidth(1);
        o2.setHeight(1);
    }
    
    @Test
    public void createRemoveGetBrickTest() {
        brickFacade.createBrick(o1);
        List<BrickDto> bList = brickFacade.getAllBricks();
        Assert.assertEquals(bList.size(), 1);
        Assert.assertTrue(bList.contains(o1));
        brickFacade.createBrick(o2);
        bList = brickFacade.getAllBricks();
        Assert.assertEquals(bList.size(), 2);
        Assert.assertTrue(bList.contains(o1));
        Assert.assertTrue(bList.contains(o2));
        brickFacade.removeBrick(o1);
        bList = brickFacade.getAllBricks();
        Assert.assertEquals(bList.size(), 1);
        Assert.assertTrue(bList.contains(o2));
        brickFacade.removeBrick(o2);
        bList = brickFacade.getAllBricks();
        Assert.assertTrue(bList.isEmpty());
    }

    @Test
    public void unusedBricksTest() {
        kitFacade.createKit(kit);
        brickFacade.createBrick(o2);
        List<BrickDto> bList = brickFacade.getUnusedBricks();
        Assert.assertEquals(bList.size(), 1);
        Assert.assertTrue(bList.contains(o2));
    }
    
    @Test
    public void mostUsedBricksTest() {
        brickFacade.createBrick(o1);
        brickFacade.createBrick(o2);
        List<BrickDto> bList = brickFacade.getMostUsedBricks(2);
        Assert.assertEquals(bList.size(), 2);
        Assert.assertTrue(bList.contains(o1));
        Assert.assertTrue(bList.contains(o2));
    }
}
