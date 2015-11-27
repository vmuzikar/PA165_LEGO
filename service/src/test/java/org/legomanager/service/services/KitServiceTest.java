package org.legomanager.service.services;

import java.util.ArrayList;
import java.util.List;
import org.legomanager.persistence.dao.AbstractBaseDao;
import org.legomanager.persistence.dao.KitDao;
import org.legomanager.persistence.entities.Kit;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests the service for manipulation with kits
 *
 * @author Michal Valeš <michal@vales.me>
 */
public class KitServiceTest extends AbstractBaseDaoServiceTest<Kit> {
    @Autowired
    @InjectMocks
    private KitService kitService;
    
    @Mock
    private KitDao kitDao;
    
    private List<Kit> listOfKits = new ArrayList<Kit>();
    
    private Kit createKit(int id, short minAge, short maxAge) {
        Kit kit = new Kit();
        kit.setId(id);
        kit.setName("Kit " + id);
        kit.setMinAge(minAge);
        kit.setMaxAge(maxAge);
        return kit;
    }
    
    @BeforeMethod
    public void initObjects() {
        listOfKits = new ArrayList<Kit>();
        for (short i = 0; i <= 20; i++) {
            listOfKits.add(createKit(i, i, (short) (i+2)));
        }
    }

    @Override
    protected AbstractBaseDaoService<Kit> getDaoService() {
        return kitService;
    }

    @Override
    protected AbstractBaseDao<Kit> getDao() {
        return kitDao;
    }

    @Override
    protected Kit createEntity() {
        return new Kit();
    }

    @Override
    protected void setIdForEntity(Kit entity, long id) {
        entity.setId(id);
    }
    
    @Test
    public void getKitsForKidsTest() {
        List<Kit> shouldBe = new ArrayList<Kit>();
        for (int i = 3; i <= 10; i++) {
            shouldBe.add(listOfKits.get(i));
        }        
        doReturn(shouldBe).when(kitDao).getKitsWithAgeRange((short) 5, (short) 10);
        Assert.assertEquals(kitService.getKitsForKids(), shouldBe);
    }
    
    @Test
    public void getKitsForTenageTest() {
        List<Kit> shouldBe = new ArrayList<Kit>();
        for (int i = 8; i <= 18; i++) {
            shouldBe.add(listOfKits.get(i));
        }        
        doReturn(shouldBe).when(kitDao).getKitsWithAgeRange((short) 10, (short) 18);
        Assert.assertEquals(kitService.getKitsForTeenage(), shouldBe);
    }
    
    @Test
    public void getKitsForAdultsTest() {
        List<Kit> shouldBe = new ArrayList<Kit>();
        for (int i = 16; i <= 20; i++) {
            shouldBe.add(listOfKits.get(i));
        }        
        doReturn(shouldBe).when(kitDao).getKitsWithAgeRange((short) 18, (short) 99);
        Assert.assertEquals(kitService.getKitsForAdults(), shouldBe);
    }
}
