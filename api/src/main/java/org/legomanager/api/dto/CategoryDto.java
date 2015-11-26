package org.legomanager.api.dto;

import java.util.HashSet;
import java.util.Set;

/**
 * Representation of a brick
 *
 * @author Michal Valeš <michal@vales.me>
 */
public class CategoryDto {
    private long id;
    private String name;
    private Set<KitDto> kits = new HashSet<KitDto>();

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
    
    public Set<KitDto> getKits() {
        return kits;
    }

    public void setKits(Set<KitDto> kits) {
        this.kits = kits;
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
