package org.legomanager;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@ContextConfiguration("classpath:META-INF/spring-config.xml")
public class AppTest extends AbstractTestNGSpringContextTests
{
    @PersistenceContext
    EntityManager entityManager;

    @Test
    public void justTesting() {
        logger.info("Hello World!");
    }
}
