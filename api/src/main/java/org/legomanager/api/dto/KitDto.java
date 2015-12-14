package org.legomanager.api.dto;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Representation of a kit
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public class KitDto {
    private long id;
    
    @NotNull(message = "Name must be filled.")
    @NotBlank(message = "Name must be filled.")
    private String name;
    
    private Long categoryId;
    
    private Set<Long> bricksIds = new HashSet<Long>();
    
    @Digits(integer = 9, fraction = 2, message = "Price must have at most two fraction digits and nine digits.")
    @DecimalMin(value = "0.00", message = "Price can't be negative.")
    private BigDecimal price;
    
    private Currency currency;
    
    @Min(value = 0, message = "Minimal age must be at least 0.")
    private short minAge;
    
    @Min(value = 0, message = "Maximal age must be at least 0.")
    @Max(value = 99, message = "Maximal age must be at most 99.")
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public Set<Long> getBricksIds() {
        return bricksIds;
    }

    public void setBricksIds(Set<Long> bricksIds) {
        this.bricksIds = bricksIds;
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
