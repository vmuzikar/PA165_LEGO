package org.legomanager.api.dto;

import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

/**
 * Representation of a brick
 *
 * @author Michal Valeš <michal@vales.me>
 */
public class BrickDto {
    private long id;

    @NotNull(message = "Name must be filled.")
    @NotBlank(message = "Name must be filled.")
    private String name;

    @Min(message = "Width must be at least 1.", value = 1)
    private int width;

    @Min(message = "Height must be at least 1.", value = 1)
    private int height;

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

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof BrickDto)) {return false;}

        BrickDto brick = (BrickDto) o;

        return brick.getName() != null && this.getName() != null && brick.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return this.getName() != null ? this.getName().hashCode() : 0;
    }
}
