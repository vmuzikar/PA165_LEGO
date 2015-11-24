package service.services;

import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;

/**
 * Base test for testing services
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@ContextConfiguration("classpath:META-INF/service-context.xml")
@Rollback(true)
public abstract class AbstractServiceTest extends AbstractTransactionalTestNGSpringContextTests {}
