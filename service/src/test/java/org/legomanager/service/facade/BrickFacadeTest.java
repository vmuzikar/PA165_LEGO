package org.legomanager.service.facade;

import java.math.BigDecimal;
import java.util.*;

import org.legomanager.api.dto.BrickDto;
import org.legomanager.api.dto.CategoryDto;
import org.legomanager.api.dto.KitDto;
import org.legomanager.api.facade.BrickFacade;
import org.legomanager.api.facade.CategoryFacade;
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

    @Autowired
    private CategoryFacade categoryFacade;
    
    private BrickDto o1, o2;
    
    @BeforeMethod
    public void initObjects() {
        o1 = new BrickDto();
        o1.setName("Brick 1");
        o1.setWidth(1);
        o1.setHeight(1);
        
        o2 = new BrickDto();
        o2.setName("Brick 2");
        o2.setWidth(1);
        o2.setHeight(1);
    }
    
    @Test
    public void createRemoveGetBrickTest() {
        long o1Id = brickFacade.createBrick(o1);
        List<BrickDto> bList = brickFacade.getAllBricks();
        Assert.assertEquals(bList.size(), 1);
        Assert.assertTrue(bList.contains(o1));
        long o2Id = brickFacade.createBrick(o2);
        bList = brickFacade.getAllBricks();
        Assert.assertEquals(bList.size(), 2);
        Assert.assertTrue(bList.contains(o1));
        Assert.assertTrue(bList.contains(o2));
        brickFacade.removeBrick(o1Id);
        bList = brickFacade.getAllBricks();
        Assert.assertEquals(bList.size(), 1);
        Assert.assertTrue(bList.contains(o2));
        brickFacade.removeBrick(o2Id);
        bList = brickFacade.getAllBricks();
        Assert.assertTrue(bList.isEmpty());
    }

    @Test
    public void unusedBricksTest() {
        brickFacade.createBrick(o1);
        long brickId = brickFacade.createBrick(o2);

        createKit(new HashSet<Long>(Arrays.asList(brickId)));

        List<BrickDto> bList = brickFacade.getUnusedBricks();
        Assert.assertEquals(bList.size(), 1);
        Assert.assertTrue(bList.contains(o1));
    }
    
    @Test
    public void mostUsedBricksTest() {
        brickFacade.createBrick(o1);
        long brickId = brickFacade.createBrick(o2);

        List<BrickDto> bList = brickFacade.getMostUsedBricks(2);
        Assert.assertEquals(bList.size(), 2);
        Assert.assertTrue(bList.contains(o1));
        Assert.assertTrue(bList.contains(o2));

        createKit(new HashSet<Long>(Arrays.asList(brickId)));

        bList = brickFacade.getMostUsedBricks(1);
        Assert.assertEquals(bList.size(), 1);
        Assert.assertTrue(bList.contains(o2));
    }

    private long createKit(Set<Long> bricks) {
        CategoryDto category = new CategoryDto();
        category.setName("Categ");
        long categId = categoryFacade.createCategory(category);
        
        KitDto kit = new KitDto();
        kit.setName("Kit");
        kit.setMinAge((short) 19);
        kit.setMaxAge((short) 79);
        kit.setCategoryId(categId);
        kit.setCurrency(Currency.getInstance("CZK"));
        kit.setPrice(new BigDecimal("2459.99"));
        kit.setBricksIds(bricks);
        return kitFacade.createKit(kit);
    }
}
