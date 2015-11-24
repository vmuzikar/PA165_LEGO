package org.legomanager.api.representantions;

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
    private int width;

    /**
     * In millimeters
     */
    private int height;

    /**
     * In millimeters
     */
    private int depth;

    public ModelRepresentation(int width, int height, int depth) {
        setWidth(width);
        setHeight(height);
        setDepth(depth);
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        if (width <= 0) {
            throw new IllegalArgumentException("Width must be greater than zero");
        }
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        if (height <= 0) {
            throw new IllegalArgumentException("Height must be greater than zero");
        }
        this.height = height;
    }

    public int getDepth() {
        return depth;
    }

    public void setDepth(int depth) {
        if (depth <= 0) {
            throw new IllegalArgumentException("Depth must be greater than zero");
        }
        this.depth = depth;
    }
}
