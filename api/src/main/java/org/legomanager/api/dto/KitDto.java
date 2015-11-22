package org.legomanager.api.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

/**
 * Representation of a kit
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class KitDto {
    private long id;
    private String name;
//    private CategoryDTO category;
//    private Set<BrickDTO> bricks = new HashSet<BrickDTO>();
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

//    public CategoryDTO getCategory() {
//        return category;
//    }
//
//    public void setCategory(CategoryDTO category) {
//        this.category = category;
//    }
//
//    public Set<BrickDTO> getBricks() {
//        return bricks;
//    }
//
//    public void setBricks(Set<BrickDTO> bricks) {
//        this.bricks = bricks;
//    }

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
}
