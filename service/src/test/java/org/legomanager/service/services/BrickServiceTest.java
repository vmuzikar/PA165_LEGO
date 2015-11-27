package org.legomanager.service.services;

import java.util.ArrayList;
import java.util.List;
import org.legomanager.persistence.dao.AbstractBaseDao;
import org.legomanager.persistence.dao.BrickDao;
import org.legomanager.persistence.entities.Brick;
import org.legomanager.persistence.entities.Kit;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests the service for manipulation with bricks
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>, Michal Valeš <michal@vales.me>
 */
public class BrickServiceTest extends AbstractBaseDaoServiceTest<Brick> {
    @Autowired
    @InjectMocks
    private BrickService brickService;
    
    @Mock
    private BrickDao brickDao;
    
    private List<Brick> listOfBricks = new ArrayList<Brick>();
    private List<Kit> listOfKits = new ArrayList<Kit>();
    
    private Brick createBrick(int id) {
        Brick brick = new Brick();
        brick.setId(id);
        brick.setName("Brick " + id);
        return brick;
    }
    
    private Kit createKit(int id) {
        Kit kit = new Kit();
        kit.setId(id);
        kit.setName("Kit " + id);
        return kit;
    }
    
    @BeforeMethod
    public void initObjects() {
        listOfBricks = new ArrayList<Brick>();
        listOfKits = new ArrayList<Kit>();
        for (int i = 1; i <= 5; i++) {
            listOfBricks.add(createBrick(i));
        }
        for (int i = 1; i <= 5; i++) {
            listOfKits.add(createKit(i));
        }
        listOfBricks.get(1).addKit(listOfKits.get(0));
        listOfBricks.get(1).addKit(listOfKits.get(1));
        listOfBricks.get(3).addKit(listOfKits.get(0));
        listOfBricks.get(4).addKit(listOfKits.get(2));
        listOfBricks.get(4).addKit(listOfKits.get(3));
        listOfBricks.get(4).addKit(listOfKits.get(4));
    }

    @Override
    protected AbstractBaseDaoService<Brick> getDaoService() {
        return brickService;
    }

    @Override
    protected AbstractBaseDao<Brick> getDao() {
        return brickDao;
    }

    @Override
    protected Brick createEntity() {
        return new Brick();
    }

    @Override
    protected void setIdForEntity(Brick entity, long id) {
        entity.setId(id);
    }
    
    @Test
    public void unusedBricksTest() {
        List<Brick> shouldBe = new ArrayList<Brick>();
        shouldBe.add(listOfBricks.get(0));
        shouldBe.add(listOfBricks.get(2));
        
        doReturn(listOfBricks).when(brickDao).findAll();
        Assert.assertEquals(brickService.getUnused(), shouldBe);
    }
    
    @Test
    public void unusedBricksTestOnEmptyDB() {
        List<Brick> shouldBe = new ArrayList<Brick>();
        doReturn(new ArrayList<Brick>()).when(brickDao).findAll();
        Assert.assertEquals(brickService.getUnused(), shouldBe);
    }
    
    @Test
    public void unusedBricksTestOnOneUnusedDBRecord() {
        List<Brick> returns = new ArrayList<Brick>();
        List<Brick> shouldBe = new ArrayList<Brick>();
        returns.add(listOfBricks.get(0));
        shouldBe.add(listOfBricks.get(0));
        doReturn(returns).when(brickDao).findAll();
        Assert.assertEquals(brickService.getUnused(), shouldBe);
    }
    
    @Test
    public void unusedBricksTestOnOneUsedDBRecord() {
        List<Brick> returns = new ArrayList<Brick>();
        List<Brick> shouldBe = new ArrayList<Brick>();
        returns.add(listOfBricks.get(1));
        doReturn(returns).when(brickDao).findAll();
        Assert.assertEquals(brickService.getUnused(), shouldBe);
    }
    
    @Test
    public void get3MostUsedBricks() {
        List<Brick> shouldBe = new ArrayList<Brick>();
        shouldBe.add(listOfBricks.get(4));
        shouldBe.add(listOfBricks.get(1));
        shouldBe.add(listOfBricks.get(3));
        doReturn(listOfBricks).when(brickDao).findAll();
        Assert.assertEquals(brickService.getMostUsed(3), shouldBe);
    }
    
    @Test
    public void getMostUsedBrick() {
        List<Brick> shouldBe = new ArrayList<Brick>();
        shouldBe.add(listOfBricks.get(4));
        doReturn(listOfBricks).when(brickDao).findAll();
        Assert.assertEquals(brickService.getMostUsed(1), shouldBe);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getMostUsedBrickWithCountLessThan1() {
        doReturn(listOfBricks).when(brickDao).findAll();
        brickService.getMostUsed(0);
    }
    
    @Test
    public void getMostUsedBricksWithCountExceeding() {
        for (int i = 0; i <= 4; i++) {
            listOfBricks.get(0).addKit(listOfKits.get(i));
        }
        List<Brick> shouldBe = new ArrayList<Brick>();
        shouldBe.add(listOfBricks.get(0));
        shouldBe.add(listOfBricks.get(4));
        shouldBe.add(listOfBricks.get(1));
        shouldBe.add(listOfBricks.get(3));
        shouldBe.add(listOfBricks.get(2));
        doReturn(listOfBricks).when(brickDao).findAll();
        Assert.assertEquals(brickService.getMostUsed(666), shouldBe);
    }
}
