package org.legomanager.api.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Representation of a brick
 *
 * @author Michal Vale� <michal@vales.me>
 */
public class CategoryDto {
    private long id;
    private String name;

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
        if (!(o instanceof CategoryDto)) {return false;}

        CategoryDto brick = (CategoryDto) o;

        return brick.getName() != null && this.getName() != null && brick.getName().equals(this.getName());
    }

    @Override
    public int hashCode() {
        return this.getName() != null ? this.getName().hashCode() : 0;
    }
}
