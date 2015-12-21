package org.legomanager.api.facade;

import org.legomanager.api.dto.BrickDto;

import java.util.List;

/**
 * Facade for manipulation with bricks
 *
 * @author Michal Vale� <michal@vales.me>
 */
public interface BrickFacade {
    
    /**
     * Creates brick. 
     *
     * @param brickDto Brick to be created.
     */
    long createBrick(BrickDto brickDto);

    void editBrick(BrickDto brickDto);
    
    /**
     * Deletes brick. 
     *
     * @param id Brick to be deleted.
     */
    void removeBrick(long id);

    BrickDto getBrick(long id);

    BrickDto getBrick(String name);

    /**
     * Fetches all bricks. 
     *
     * @return List of all bricks.
     */
    List<BrickDto> getAllBricks();
    
    /**
     * Fetches all bricks which aren't used in any kit. 
     *
     * @return List of all bricks which aren't used in any kit.
     */
    List<BrickDto> getUnusedBricks();
    
    /**
     * Fetches bricks sorted by number of kits which they are used in. 
     *
     * @param count Number of bricks to be fetched. Count has to be at least 1, 
     *              if it's less, IllegalArgumentException is thrown. When count 
     *              exceeds number of bricks, all bricks are fetched.
     * @return List of bricks sorted by number of kits which they are used in.
     */
    List<BrickDto> getMostUsedBricks(int count);
    
}
