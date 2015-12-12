package org.legomanager.web.restapi.resources;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.legomanager.api.dto.BrickDto;
import org.springframework.hateoas.ResourceSupport;

/**
 * Brick HATEOAS for REST API
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class BrickResource extends ResourceSupport {
    @JsonProperty("id")
    private long dtoId;
    private String name;
    private int width;
    private int height;

    public BrickResource(BrickDto brickDto) {
        dtoId = brickDto.getId();
        name = brickDto.getName();
        width = brickDto.getWidth();
        height = brickDto.getHeight();
    }

    public long getDtoId() {
        return dtoId;
    }

    public void setDtoId(long dtoId) {
        this.dtoId = dtoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
}
