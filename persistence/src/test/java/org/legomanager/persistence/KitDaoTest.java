package org.legomanager.persistence;

import org.legomanager.persistence.dao.AbstractBaseDao;
import org.legomanager.persistence.dao.KitDao;
import org.legomanager.persistence.entities.Kit;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Tests for Kit DAO
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class KitDaoTest extends AbstractBaseDaoTest<Kit> {
    @Autowired
    private KitDao kitDao;

    @Override
    protected AbstractBaseDao<Kit> getDao() {
        return kitDao;
    }

    @Override
    protected Kit createEntity() {
        return createKit();
    }

    @Override
    protected long getIdFromEntity(Kit entity) {
        return entity.getId();
    }

    @Override
    protected void setSomeValueForEntity(Kit entity) {
        entity.setName(entity.getName() + "New");
    }

    @Test
    public void getKitsWithAgeRangeTest() {
        Kit kit1 = createKit();
        kit1.setMinAge((short) 1);
        kit1.setMaxAge((short) 3);
        kitDao.create(kit1);

        Kit kit2 = createKit();
        kit2.setMinAge((short) 2);
        kit2.setMaxAge((short) 10);
        kitDao.create(kit2);

        Kit kit3 = createKit();
        kit3.setMinAge((short) 18);
        kit3.setMaxAge((short) 99);
        kitDao.create(kit3);

        List<Kit> foundKits = kitDao.getKitsWithAgeRange((short) 4, (short) 7);
        Assert.assertEquals(foundKits, Arrays.asList(kit2));

        foundKits = kitDao.getKitsWithAgeRange((short) 0, (short) 20);
        Assert.assertEquals(foundKits, Arrays.asList(kit1, kit2, kit3));

        foundKits = kitDao.getKitsWithAgeRange((short) 101, (short) 120);
        Assert.assertEquals(foundKits, Collections.emptyList());
    }
}
