package org.legomanager.service.facade;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

import org.legomanager.api.dto.CategoryDto;
import org.legomanager.api.dto.KitDto;
import org.legomanager.api.facade.CategoryFacade;
import org.legomanager.api.facade.KitFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests the service for kit facade
 *
 * @author Michal Vale� <michal@vales.me>
 */
@ContextConfiguration("classpath:META-INF/service-context.xml")
@Rollback(true)
public class KitFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Autowired
    private KitFacade kitFacade;

    @Autowired
    private CategoryFacade categoryFacade;
    
    private KitDto o1, o2;

    @BeforeMethod
    public void initObjects() {
        CategoryDto category = new CategoryDto();
        category.setName("Categ");
        long categId = categoryFacade.createCategory(category);

        o1 = new KitDto();
        o1.setName("Kit1");
        o1.setMinAge((short) 19);
        o1.setMaxAge((short) 79);
        o1.setCategoryId(categId);
        o1.setCurrency(Currency.getInstance("CZK"));
        o1.setPrice(new BigDecimal("2459.99"));

        o2 = new KitDto();
        o2.setName("Kit2");
        o2.setMinAge((short) 1);
        o2.setMaxAge((short) 9);
        o2.setCategoryId(categId);
        o2.setCurrency(Currency.getInstance("CZK"));
        o2.setPrice(new BigDecimal("2459.99"));
    }
    
    
    @Test
    public void createRemoveGetBrickTest() {
        long o1Id = kitFacade.createKit(o1);
        List<KitDto> kList = kitFacade.getAllKits();
        Assert.assertEquals(kList.size(), 1);
        Assert.assertTrue(kList.contains(o1));
        long o2Id = kitFacade.createKit(o2);
        kList = kitFacade.getAllKits();
        Assert.assertEquals(kList.size(), 2);
        Assert.assertTrue(kList.contains(o1));
        Assert.assertTrue(kList.contains(o2));
        kitFacade.removeKit(o1Id);
        kList = kitFacade.getAllKits();
        Assert.assertEquals(kList.size(), 1);
        Assert.assertTrue(kList.contains(o2));
        kitFacade.removeKit(o2Id);
        kList = kitFacade.getAllKits();
        Assert.assertTrue(kList.isEmpty());
    }

    @Test
    public void getKitsForKidsTest() {
        kitFacade.createKit(o1);
        kitFacade.createKit(o2);
        List<KitDto> kList = kitFacade.getKitsForKids();
        Assert.assertEquals(kList.size(), 1);
        Assert.assertTrue(kList.contains(o2));
    }
    
    @Test
    public void getKitsForTeenageTest() {
        kitFacade.createKit(o1);
        kitFacade.createKit(o2);
        List<KitDto> kList = kitFacade.getKitsForTeenage();
        Assert.assertTrue(kList.isEmpty());
    }
    
    @Test
    public void getKitsForAdultsTest() {
        kitFacade.createKit(o1);
        kitFacade.createKit(o2);
        List<KitDto> kList = kitFacade.getKitsForAdults();
        Assert.assertEquals(kList.size(), 1);
        Assert.assertTrue(kList.contains(o1));
    }

}