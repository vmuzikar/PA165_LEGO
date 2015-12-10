package org.legomanager.service.services;

import org.dozer.Mapper;
import org.legomanager.api.dto.BrickDto;
import org.legomanager.persistence.entities.Brick;
import org.legomanager.persistence.entities.Kit;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Test for bean mapping
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class BeanMappingTest extends AbstractServiceTest {
    @Autowired
    private BeanMappingService mappingService;

    private int bricksCount;
    private int kitsCount;

    private Brick createBrick() {
        Brick brick = new Brick();
        brick.setName("Brick" + bricksCount++);
        for (int i = 0; i < 10; i++) {
            Kit kit = new Kit();
            kit.setName("Kit" + kitsCount++);
            brick.addKit(kit);
        }
        return brick;
    }

    @Test
    public void simpleMappingTest() {
        Brick brick = createBrick();
        BrickDto brickDto = mappingService.map(brick, BrickDto.class);

        Assert.assertEquals(brickDto.getName(), brick.getName());
    }

    @Test
    public void collectionMappingTest() {
        List<Brick> bricks = new ArrayList<Brick>();
        for (int i = 0; i < 10; i++) {
            bricks.add(createBrick());
        }

        List<BrickDto> brickDtos = (List<BrickDto>) mappingService.map(bricks, BrickDto.class);

        Assert.assertEquals(brickDtos.size(), bricks.size());
        int i = 0;
        for (BrickDto brickDto : brickDtos) {
            Assert.assertEquals(brickDto.getName(), bricks.get(i++).getName());
        }
    }
}
