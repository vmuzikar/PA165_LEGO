package org.legomanager.service.facade;

import java.util.List;
import org.legomanager.api.dto.BrickDto;
import org.legomanager.persistence.entities.Brick;
import org.legomanager.api.facade.BrickFacade;
import org.legomanager.service.services.BeanMappingService;
import org.legomanager.service.services.BrickService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of facade for manipulation with bricks
 *
 * @author Michal Vale� <michal@vales.me>
 */
@Service
@Transactional
public class BrickFacadeImpl implements BrickFacade {
    
    @Autowired
    BrickService brickService;

    @Autowired
    BeanMappingService mappingService;

    public void createBrick(BrickDto brickDto) {
        Brick brick = mappingService.map(brickDto, Brick.class);
        brickService.create(brick);
        brickDto.setId(brick.getId());
    }

    public void removeBrick(BrickDto brickDto) {
        Brick brick = mappingService.map(brickDto, Brick.class);
        brickService.remove(brick);
    }

    public List<BrickDto> getAllBricks() {
        return (List<BrickDto>) mappingService.map(brickService.findAll(), BrickDto.class);
    }

    public List<BrickDto> getUnusedBricks() {
        return (List<BrickDto>) mappingService.map(brickService.getUnused(), BrickDto.class);
    }

    public List<BrickDto> getMostUsedBricks(int count) {
        return (List<BrickDto>) mappingService.map(brickService.getMostUsed(count), BrickDto.class);
    }
}
