package org.legomanager.service.services;

import org.mockito.MockitoAnnotations;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeClass;

/**
 * Base test class for testing services
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@ContextConfiguration("classpath:META-INF/service-context.xml")
@Rollback(true)
public abstract class AbstractServiceTest extends AbstractTestNGSpringContextTests {
    @BeforeClass
    public void initServiceTests() {
        MockitoAnnotations.initMocks(this);
    }
}
