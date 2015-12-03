package org.legomanager.service.facade;

import java.math.BigDecimal;
import java.util.*;

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
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests the service for category facade
 *
 * @author Michal Vale� <michal@vales.me>
 */
@ContextConfiguration("classpath:META-INF/service-context.xml")
@Rollback(true)
public class CategoryFacadeTest extends AbstractTransactionalTestNGSpringContextTests {
    
    @Autowired
    private CategoryFacade categoryFacade;

    @Autowired
    private KitFacade kitFacade;

    private KitDto kit;
    
    private CategoryDto o1, o2;
    
    @BeforeMethod
    public void initObjects() {
        o1 = new CategoryDto();
        o1.setName("C 1");

        kit = new KitDto();
        kit.setName("Kit");
        kit.setCategory(o1);
        kit.setMinAge((short) 1);
        kit.setMaxAge((short) 9);
        kit.setCurrency(Currency.getInstance("CZK"));
        kit.setPrice(new BigDecimal("2459.99"));
        
        o2 = new CategoryDto();
        o2.setName("C 2");
    }
    
    @Test
    public void createRemoveGetCategoryTest() {
        categoryFacade.createCategory(o1);
        List<CategoryDto> cList = categoryFacade.getAllCategories();
        Assert.assertEquals(cList.size(), 1);
        Assert.assertTrue(cList.contains(o1));
        categoryFacade.createCategory(o2);
        cList = categoryFacade.getAllCategories();
        Assert.assertEquals(cList.size(), 2);
        Assert.assertTrue(cList.contains(o1));
        Assert.assertTrue(cList.contains(o2));
        categoryFacade.removeCategory(o1);
        cList = categoryFacade.getAllCategories();
        Assert.assertEquals(cList.size(), 1);
        Assert.assertTrue(cList.contains(o2));
        categoryFacade.removeCategory(o2);
        cList = categoryFacade.getAllCategories();
        Assert.assertTrue(cList.isEmpty());
    }

    @Test
    public void unusedCategoryTest() {
        kitFacade.createKit(kit);
        categoryFacade.createCategory(o2);
        List<CategoryDto> cList = categoryFacade.getUnusedCategories();
        Assert.assertEquals(cList.size(), 1);
        Assert.assertTrue(cList.contains(o2));
    }
    
    @Test
    public void categoryWithMostKitsTest() {
        kitFacade.createKit(kit);
        categoryFacade.createCategory(o2);
        List<CategoryDto> cList = categoryFacade.getCategoriesWithMostKits(1);
        Assert.assertEquals(cList.size(), 1);
        Assert.assertTrue(cList.contains(o1));
    }
    
    @Test
    public void mergeTest() {
        categoryFacade.createCategory(o1);
        categoryFacade.createCategory(o2);
        List<Long> l = new ArrayList<Long>();
        l.add(o2.getId());
        CategoryDto c = categoryFacade.merge(o1.getId(), l);
        Assert.assertEquals(c.getKits().size(), 0);
    }

}
