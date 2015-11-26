package service.services;

import org.legomanager.persistence.entities.Brick;
import org.legomanager.service.services.AbstractBaseDaoService;
import org.legomanager.service.services.BrickService;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Tests the service for manipulation with kits
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class BrickServiceTest extends AbstractBaseDaoServiceTest<Brick> {
    @Autowired
    @InjectMocks
    private BrickService brickService;

    @Override
    protected AbstractBaseDaoService<Brick> getDaoService() {
        return brickService;
    }

    @Override
    protected Brick createEntity() {
        return new Brick();
    }

    @Override
    protected void setIdForEntity(Brick entity, long id) {
        entity.setId(id);
    }
}
