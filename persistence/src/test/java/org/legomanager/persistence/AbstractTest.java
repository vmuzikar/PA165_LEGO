package org.legomanager.persistence;

import org.legomanager.persistence.entities.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Base test
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@ContextConfiguration("classpath:META-INF/persistence-context.xml")
public abstract class AbstractTest extends AbstractTransactionalTestNGSpringContextTests{
    
    protected Brick createBrick() {
        Brick brick = new Brick();
        brick.setName("Some brick");
        return brick;
    }

    protected Kit createKit(Category category) {
        Kit kit = new Kit();
        kit.setName("Some kit");
        kit.setCurrency(Currency.getInstance("CZK"));
        kit.setPrice(new BigDecimal("2459.99"));
        kit.setMinAge((short) 3);
        kit.setMaxAge((short) 12);
        kit.setCategory(category);
        return kit;
    }

    protected Category createCategory() {
        Category category = new Category();
        category.setName("Some categ");
        return category;
    }
}
