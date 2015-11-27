package org.legomanager.persistence;

import org.legomanager.persistence.dao.AbstractBaseDao;
import org.legomanager.persistence.dao.BrickDao;
import org.legomanager.persistence.entities.Brick;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * Tests for Brick DAO
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class BrickDaoTest extends AbstractBaseDaoTest<Brick> {
    @Autowired
    private BrickDao brickDao;

    @Override
    protected AbstractBaseDao<Brick> getDao() {
        return brickDao;
    }

    @Override
    protected Brick createEntity() {
        return createBrick();
    }

    @Override
    protected long getIdFromEntity(Brick entity) {
        return entity.getId();
    }

    @Override
    protected void setSomeValueForEntity(Brick entity) {
        entity.setName(entity.getName() + "New");
    }

    @Test
    public void getLargestBrickTest() {
        Brick brick1 = createBrick();
        brick1.setWidth(1);
        brick1.setHeight(1);

        Brick brick2 = createBrick();
        brick2.setWidth(10);
        brick2.setHeight(9);

        Brick brick3 = createBrick();
        brick3.setWidth(6);
        brick3.setHeight(7);

        brickDao.create(brick1);
        brickDao.create(brick2);
        brickDao.create(brick3);

        Brick foundBrick = brickDao.getLargestBrick(8, 9);
        Assert.assertEquals(foundBrick, brick3);

        foundBrick = brickDao.getLargestBrick(9, 10);
        Assert.assertEquals(foundBrick, brick2);
    }
}
