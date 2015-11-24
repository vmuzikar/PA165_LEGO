package service.services;

import org.legomanager.api.exceptions.ModelKitConverterException;
import org.legomanager.api.representantions.ModelRepresentation;
import org.legomanager.persistence.dao.BrickDao;
import org.legomanager.persistence.entities.Brick;
import org.legomanager.persistence.entities.Kit;
import org.legomanager.service.services.ModelKitConverterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

/**
 * Tests the service for converting 3D models to kits
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class ModelKitConverterTest extends AbstractServiceTest {

    @Autowired
    private BrickDao brickDao;

    private int brickCount = 1;

    @Autowired
    private ModelKitConverterService converterService;

    private Brick createBrick(int width, int height) {
        Brick brick = new Brick();
        brick.setName("Brick" + brickCount++);
        brick.setWidth(width);
        brick.setHeight(height);

        brickDao.create(brick);

        return brick;
    }

    @Test
    public void modelToKitTest() {
        Set<Brick> bricks = new HashSet<Brick>();

        bricks.add(createBrick(4,5));
        bricks.add(createBrick(4,2));
        bricks.add(createBrick(7,1));

        // Shouldn't use
        createBrick(1,1);
        createBrick(3,1);
        createBrick(1,4);
        createBrick(2,2);

        ModelRepresentation model = new ModelRepresentation(56,40,10);
        Kit kit = converterService.convertModelToKit(model);

        Assert.assertEquals(kit.getBricks(), bricks);
    }

    @Test(expectedExceptions = ModelKitConverterException.class)
    public void modelToKitFailTest() {
        createBrick(4,5);
        createBrick(4,2);
        createBrick(7,1);

        converterService.convertModelToKit(new ModelRepresentation(20,20,10));
    }
}
