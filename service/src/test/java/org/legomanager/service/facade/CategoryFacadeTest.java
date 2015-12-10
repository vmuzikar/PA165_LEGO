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

    private CategoryDto o1, o2;

    private short kitsCounter = 1;

    @BeforeMethod
    public void initObjects() {
        o1 = new CategoryDto();
        o1.setName("C 1");
        
        o2 = new CategoryDto();
        o2.setName("C 2");
    }
    
    @Test
    public void createRemoveGetCategoryTest() {
        long o1Id = categoryFacade.createCategory(o1);
        List<CategoryDto> cList = categoryFacade.getAllCategories();
        Assert.assertEquals(cList.size(), 1);
        Assert.assertTrue(cList.contains(o1));
        long o2Id = categoryFacade.createCategory(o2);
        cList = categoryFacade.getAllCategories();
        Assert.assertEquals(cList.size(), 2);
        Assert.assertTrue(cList.contains(o1));
        Assert.assertTrue(cList.contains(o2));
        categoryFacade.removeCategory(o1Id);
        cList = categoryFacade.getAllCategories();
        Assert.assertEquals(cList.size(), 1);
        Assert.assertTrue(cList.contains(o2));
        categoryFacade.removeCategory(o2Id);
        cList = categoryFacade.getAllCategories();
        Assert.assertTrue(cList.isEmpty());
    }

    @Test
    public void unusedCategoryTest() {
        long categId = categoryFacade.createCategory(o1);
        categoryFacade.createCategory(o2);

        createKit(categId);

        List<CategoryDto> cList = categoryFacade.getUnusedCategories();
        Assert.assertEquals(cList.size(), 1);
        Assert.assertTrue(cList.contains(o2));
    }
    
    @Test
    public void categoryWithMostKitsTest() {
        long categId = categoryFacade.createCategory(o1);
        categoryFacade.createCategory(o2);

        List<CategoryDto> cList = categoryFacade.getCategoriesWithMostKits(2);
        Assert.assertEquals(cList.size(), 2);
        Assert.assertTrue(cList.contains(o1));
        Assert.assertTrue(cList.contains(o2));

        createKit(categId);

        cList = categoryFacade.getCategoriesWithMostKits(1);
        Assert.assertEquals(cList.size(), 1);
        Assert.assertTrue(cList.contains(o1));
    }
    
    @Test
    public void mergeTest() {
        CategoryDto category3 = new CategoryDto();
        category3.setName("Categ3");

        long categ1Id = categoryFacade.createCategory(o1);
        long categ2Id = categoryFacade.createCategory(o2);
        long categ3Id = categoryFacade.createCategory(category3);

        long kit1Id = createKit(categ1Id);
        long kit2Id = createKit(categ2Id);

        categoryFacade.merge(categ3Id, Arrays.asList(categ1Id, categ2Id));

        KitDto kit1 = kitFacade.getKit(kit1Id);
        KitDto kit2 = kitFacade.getKit(kit2Id);

        Assert.assertEquals((long) kit1.getCategoryId(), categ3Id);
        Assert.assertEquals((long) kit2.getCategoryId(), categ3Id);
    }

    private long createKit(long category) {
        KitDto kit = new KitDto();
        kit.setName("Kit" + kitsCounter++);
        kit.setMinAge((short) 19);
        kit.setMaxAge((short) 79);
        kit.setCategoryId(category);
        kit.setCurrency(Currency.getInstance("CZK"));
        kit.setPrice(new BigDecimal("2459.99"));
        return kitFacade.createKit(kit);
    }
}
