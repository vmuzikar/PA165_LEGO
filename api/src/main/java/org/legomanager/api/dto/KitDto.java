package org.legomanager.api.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Representation of a kit
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class KitDto {
    private long id;
    private String name;
    private CategoryDto category;
    private Set<BrickDto> bricks = new HashSet<BrickDto>();
    private BigDecimal price;
    private Currency currency;
    private short minAge;
    private short maxAge;

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

    public CategoryDto getCategory() {
        return category;
    }

    public void setCategory(CategoryDto category) {
        this.category = category;
    }

    public Set<BrickDto> getBricks() {
        return bricks;
    }

    public void setBricks(Set<BrickDto> bricks) {
        this.bricks = bricks;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public short getMinAge() {
        return minAge;
    }

    public void setMinAge(short minAge) {
        this.minAge = minAge;
    }

    public short getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(short maxAge) {
        this.maxAge = maxAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof KitDto)) {return false;}

        KitDto kit = (KitDto) o;

        return kit.getName() != null && getName() != null && kit.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
