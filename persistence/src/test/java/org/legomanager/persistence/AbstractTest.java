package org.legomanager.persistence;

import org.legomanager.persistence.entities.Brick;
import org.legomanager.persistence.entities.Category;
import org.legomanager.persistence.entities.Kit;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Base test class for testing DAOs
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Rollback(true)
@ContextConfiguration("classpath:META-INF/persistence-context.xml")
public abstract class AbstractTest extends AbstractTransactionalTestNGSpringContextTests {
    private int bricksCount, categoriesCount, kitsCount;

    protected Brick createBrick() {
        Brick brick = new Brick();
        brick.setName("Brick" + bricksCount++);
        brick.setWidth(2);
        brick.setHeight(2);
        return brick;
    }

    protected Category createCategory() {
        Category category = new Category();
        category.setName("Category" + categoriesCount++);
        return category;
    }

    protected Kit createKit() {
        Kit kit = new Kit();
        kit.setName("Kit" + kitsCount++);
        kit.setCurrency(Currency.getInstance("CZK"));
        kit.setPrice(new BigDecimal("2459.99"));
        kit.setMinAge((short) 3);
        kit.setMaxAge((short) 12);
        kit.setCategory(createCategory());
        for (int i = 0; i < 10; i++) {
            kit.addBrick(createBrick());
        }
        return kit;
    }
}
