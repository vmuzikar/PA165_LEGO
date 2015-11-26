package org.legomanager.service.services;

import org.legomanager.api.exceptions.ModelKitConverterException;
import org.legomanager.api.representantions.ModelRepresentation;
import org.legomanager.persistence.dao.BrickDao;
import org.legomanager.persistence.entities.Brick;
import org.legomanager.persistence.entities.Kit;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;
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
public class ModelKitConverterServiceTest extends AbstractServiceTest {

    @Mock
    private BrickDao brickDao;

    private int brickCount = 1;

    @Autowired
    @InjectMocks
    private ModelKitConverterService converterService;

    private Brick createBrick(int width, int height) {
        Brick brick = new Brick();
        brick.setId(brickCount);
        brick.setName("Brick" + brickCount++);
        brick.setWidth(width);
        brick.setHeight(height);

        return brick;
    }

    @Test
    public void modelToKitTest() {
        Set<Brick> bricksShouldUse = new HashSet<Brick>();

        bricksShouldUse.add(createBrick(4,5));
        bricksShouldUse.add(createBrick(4,2));
        bricksShouldUse.add(createBrick(7,1));

        // Shouldn't use
        Set<Brick> bricksAll = new HashSet<Brick>(bricksShouldUse);
        bricksAll.add(createBrick(1,1));
        bricksAll.add(createBrick(3,1));
        bricksAll.add(createBrick(1,4));
        bricksAll.add(createBrick(2,2));

        ModelRepresentation model = new ModelRepresentation(56,40,10);

        Mockito.when(brickDao.getLargestBrick(Mockito.anyInt(), Mockito.anyInt())).thenAnswer(
                new LargestBrickSimulator(bricksAll)
        );

        Kit kit = converterService.convertModelToKit(model);

        Assert.assertEquals(kit.getBricks(), bricksShouldUse);
    }

    @Test(expectedExceptions = ModelKitConverterException.class)
    public void modelToKitFailTest() {
        Set<Brick> bricksAll = new HashSet<Brick>();
        bricksAll.add(createBrick(4,5));
        bricksAll.add(createBrick(4,2));
        bricksAll.add(createBrick(7,1));

        Mockito.when(brickDao.getLargestBrick(Mockito.anyInt(), Mockito.anyInt())).thenAnswer(
                new LargestBrickSimulator(bricksAll)
        );

        converterService.convertModelToKit(new ModelRepresentation(20,20,10));
    }

    private class LargestBrickSimulator implements Answer {
        private Set<Brick> bricks;

        public LargestBrickSimulator(Set<Brick> bricks) {
            this.bricks = bricks;
        }

        public Object answer(InvocationOnMock invocationOnMock) throws Throwable {
            Object[] args = invocationOnMock.getArguments();
            int stubsX = (Integer) args[0];
            int stubsY = (Integer) args[1];

            Brick largestBrick = null;
            for (Brick brick : bricks) {
                if (
                    (
                        (brick.getHeight() <= stubsX && brick.getWidth() <= stubsY)
                        ||
                        (brick.getHeight() <= stubsY && brick.getWidth() <= stubsX)
                    )
                    &&
                    (
                        largestBrick == null
                        ||
                        (largestBrick.getWidth() * largestBrick.getHeight()) < (brick.getWidth() * brick.getHeight())
                    )
                ) {
                    largestBrick = brick;
                }
            }

            return largestBrick;
        }
    }
}
