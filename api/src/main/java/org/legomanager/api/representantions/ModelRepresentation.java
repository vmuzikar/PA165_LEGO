package org.legomanager.api.representantions;

import javax.validation.constraints.Min;

/**
 * Representation of a 3D model
 * This model can be imported to LEGO manager and transformed to a LEGO kit
 * This representation is very simplified for purposes of PA165 - it's just a regular cuboid.
 * Since we shouldn't work directly with DTO in services this is not a DTO
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class ModelRepresentation {
    /**
     * In millimeters
     */
    @Min(value = 1, message = "Width must be at least 1")
    private int width;

    /**
     * In millimeters
     */
    @Min(value = 1, message = "Width must be at least 1")
    private int height;

    /**
     * In millimeters
     */
    @Min(value = 1, message = "Width must be at least 1")
    private int depth;

    public ModelRepresentation() {}

    public ModelRepresentation(int width, int height, int depth) {
        setWidth(width);
        setHeight(height);
        setDepth(depth);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        this.depth = depth;
    }
}
