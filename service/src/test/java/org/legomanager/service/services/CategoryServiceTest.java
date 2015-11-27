package org.legomanager.service.services;

import java.util.ArrayList;
import java.util.List;
import org.legomanager.persistence.dao.AbstractBaseDao;
import org.legomanager.persistence.dao.CategoryDao;
import org.legomanager.persistence.entities.Category;
import org.legomanager.persistence.entities.Kit;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * Tests the service for manipulation with categories
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class CategoryServiceTest extends AbstractBaseDaoServiceTest<Category> {
    @Autowired
    @InjectMocks
    private CategoryService categoryService;
    
    @Mock
    private CategoryDao categoryDao;
    
    private List<Category> listOfCategories = new ArrayList<Category>();
    private List<Kit> listOfKits = new ArrayList<Kit>();
    
    private Category createCategory(int id) {
        Category category = new Category();
        category.setId(id);
        category.setName("Category " + id);
        return category;
    }
    
    private Kit createKit(int id) {
        Kit kit = new Kit();
        kit.setId(id);
        kit.setName("Kit " + id);
        return kit;
    }
    
    @BeforeMethod
    public void initObjects() {
        listOfCategories = new ArrayList<Category>();
        listOfKits = new ArrayList<Kit>();
        for (int i = 1; i <= 5; i++) {
            listOfCategories.add(createCategory(i));
        }
        for (int i = 1; i <= 5; i++) {
            listOfKits.add(createKit(i));
        }
        listOfCategories.get(1).addKit(listOfKits.get(0));
        listOfCategories.get(1).addKit(listOfKits.get(1));
        listOfCategories.get(3).addKit(listOfKits.get(0));
        listOfCategories.get(4).addKit(listOfKits.get(2));
        listOfCategories.get(4).addKit(listOfKits.get(3));
        listOfCategories.get(4).addKit(listOfKits.get(4));
    }

    @Override
    protected AbstractBaseDaoService<Category> getDaoService() {
        return categoryService;
    }

    @Override
    protected AbstractBaseDao<Category> getDao() {
        return categoryDao;
    }

    @Override
    protected Category createEntity() {
        return new Category();
    }

    @Override
    protected void setIdForEntity(Category entity, long id) {
        entity.setId(id);
    }
    
    @Test
    public void categoriesWithNoKitsTest() {
        List<Category> shouldBe = new ArrayList<Category>();
        shouldBe.add(listOfCategories.get(0));
        shouldBe.add(listOfCategories.get(2));
        
        doReturn(listOfCategories).when(categoryDao).findAll();
        Assert.assertEquals(categoryService.getUnused(), shouldBe);
    }
    
    @Test
    public void categoriesWithNoKitsTestOnEmptyDB() {
        List<Category> shouldBe = new ArrayList<Category>();
        doReturn(new ArrayList<Category>()).when(categoryDao).findAll();
        Assert.assertEquals(categoryService.getUnused(), shouldBe);
    }
    
    @Test
    public void categoriesWithNoKitsTestOnOneDBRecordWithNoKit() {
        List<Category> returns = new ArrayList<Category>();
        List<Category> shouldBe = new ArrayList<Category>();
        returns.add(listOfCategories.get(0));
        shouldBe.add(listOfCategories.get(0));
        doReturn(returns).when(categoryDao).findAll();
        Assert.assertEquals(categoryService.getUnused(), shouldBe);
    }
    
    @Test
    public void categoriesWithNoKitsTestOnOneDBRecordWithKits() {
        List<Category> returns = new ArrayList<Category>();
        List<Category> shouldBe = new ArrayList<Category>();
        returns.add(listOfCategories.get(1));
        doReturn(returns).when(categoryDao).findAll();
        Assert.assertEquals(categoryService.getUnused(), shouldBe);
    }
    
    @Test
    public void get3MostUsedCategories() {
        List<Category> shouldBe = new ArrayList<Category>();
        shouldBe.add(listOfCategories.get(4));
        shouldBe.add(listOfCategories.get(1));
        shouldBe.add(listOfCategories.get(3));
        doReturn(listOfCategories).when(categoryDao).findAll();
        Assert.assertEquals(categoryService.getWithMostKits(3), shouldBe);
    }
    
    @Test
    public void getMostUsedCategory() {
        List<Category> shouldBe = new ArrayList<Category>();
        shouldBe.add(listOfCategories.get(4));
        doReturn(listOfCategories).when(categoryDao).findAll();
        Assert.assertEquals(categoryService.getWithMostKits(1), shouldBe);
    }
    
    @Test(expectedExceptions = IllegalArgumentException.class)
    public void getMostUsedCategoriesWithCountLessThan1() {
        doReturn(listOfCategories).when(categoryDao).findAll();
        categoryService.getWithMostKits(0);
    }
    
    @Test
    public void getMostUsedCategoriesWithCountExceeding() {
        for (int i = 0; i <= 4; i++) {
            listOfCategories.get(0).addKit(listOfKits.get(i));
        }
        List<Category> shouldBe = new ArrayList<Category>();
        shouldBe.add(listOfCategories.get(0));
        shouldBe.add(listOfCategories.get(4));
        shouldBe.add(listOfCategories.get(1));
        shouldBe.add(listOfCategories.get(3));
        shouldBe.add(listOfCategories.get(2));
        doReturn(listOfCategories).when(categoryDao).findAll();
        Assert.assertEquals(categoryService.getWithMostKits(666), shouldBe);
    }
    
    @Test
    public void mergeCategoriesTest() {
        List<Category> mergeWith = new ArrayList<Category>();
        mergeWith.add(listOfCategories.get(3));
        Category c = categoryService.merge(listOfCategories.get(1), mergeWith);
        Assert.assertTrue(c.getKits().contains(listOfKits.get(0)));
        Assert.assertTrue(c.getKits().contains(listOfKits.get(1)));
        Assert.assertTrue(c.getKits().size() == 2);
        verify(getDao()).delete(listOfCategories.get(1));
        verify(getDao()).delete(listOfCategories.get(3));
        verify(getDao()).create(c);
    }
}
