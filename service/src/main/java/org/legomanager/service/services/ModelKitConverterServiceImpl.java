package org.legomanager.service.services;

import org.legomanager.api.exceptions.ModelKitConverterException;
import org.legomanager.api.exceptions.ServiceException;
import org.legomanager.api.representantions.ModelRepresentation;
import org.legomanager.persistence.dao.BrickDao;
import org.legomanager.persistence.entities.Brick;
import org.legomanager.persistence.entities.Kit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashSet;
import java.util.Set;

/**
 * Service for converting 3D models to kits
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Service
public class ModelKitConverterServiceImpl implements ModelKitConverterService {
    /**
     * In millimeters
     * We have constant height for all bricks - they differs only by number of stubs
     */
    public static final int STUB_WIDTH_HEIGHT = 8;

    @Autowired
    private BrickDao brickDao;

    /**
     * Converts a 3D model to a Kit
     *
     * @param model representation of a 3D model
     */
    public Kit convertModelToKit(ModelRepresentation model) {
        // Convert millimeters to LEGO stubs
        int stubsX = model.getWidth() / STUB_WIDTH_HEIGHT;
        int stubsY = model.getHeight() / STUB_WIDTH_HEIGHT;

        Set<Brick> usedBricks = new HashSet<Brick>();
        constructArea(stubsX, stubsY, usedBricks);

        if (usedBricks.isEmpty()) {
            throw new ModelKitConverterException("Conversion failed due to lack of suitable bricks");
        }

        Kit kit = new Kit();
        for (Brick brick : usedBricks) {
            kit.addBrick(brick);
        }

        return kit;
    }

    /**
     * Conversion algorithm
     * Tries to construct using as large bricks as possible - uses only one brick type.
     * It's very, very simplified for the purposes of PA165.
     * E.g. since we don't store the count and depth (suppose all bricks have the same depth) of bricks
     * we don't need to calculate "layers" of the construction - we construct the base
     * and then every layer will be composed of the same bricks as the base.
     *
     * @param stubsX
     * @param stubsY
     * @param usedBricks
     */
    private void constructArea(int stubsX, int stubsY, Set<Brick> usedBricks) {
        // Stops the recursion
        if (stubsX == 0 || stubsY == 0) {
            return;
        }

        // Normalization; in this algorithm we always take X (or the "first" value)
        // as the larger value, i.e. we can rotate the bricks
        int tmp;
        if (stubsY > stubsX) {
            tmp = stubsX;
            stubsX = stubsY;
            stubsY = tmp;
        }

        // We try to find a largest suitable brick
        Brick largestBrick = brickDao.getLargestBrick(stubsX, stubsY);

        // We didn't find any suitable brick = there will be a gap in the construction
        if (largestBrick == null) {
            return;
        }

        usedBricks.add(largestBrick);

        // Normalization, i.e. rotation of the brick
        int brickSizeX, brickSizeY;
        brickSizeX = largestBrick.getWidth();
        brickSizeY = largestBrick.getHeight();
        if (brickSizeY > brickSizeX) {
            tmp = brickSizeX;
            brickSizeX = brickSizeY;
            brickSizeY = tmp;
        }

        // Areas we need to fill with bricks of different (smaller) sizes
        int remainderX = stubsX - brickSizeX;
        int remainderY = stubsY - brickSizeY;

        // Construct remaining empty areas which can't be filled
        // with the current size of the brick (largestBrick)
        constructArea(stubsX, remainderY, usedBricks);
        constructArea(remainderX, stubsY - remainderY, usedBricks);
    }
}
