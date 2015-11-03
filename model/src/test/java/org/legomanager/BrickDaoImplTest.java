package org.legomanager;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * @author Michal Valeš <michal@vales.me>
 */
public class BrickDaoImplTest {
    
    private BrickDao brickDao;
    
    @Before
    public void init() {
        brickDao = new BrickDaoImpl();
    }
    
    @Test
    public void testCreate() {
        // TODO
        assertEquals(0, 0);
    }
}
