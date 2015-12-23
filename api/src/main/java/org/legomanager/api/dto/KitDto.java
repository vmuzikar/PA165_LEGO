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
import org.hibernate.validator.constraints.NotEmpty;

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

    @NotNull(message = "Category must be selected")
    private Long categoryId;

    /**
     * Not used for write operations, i.e. it's only for passing the data FROM database
     */
    private CategoryDto categoryDto;

    @NotEmpty(message = "At least one brick must be selected")
    private Set<Long> bricksIds = new HashSet<Long>();

    /**
     * Not used for write operations, i.e. it's only for passing the data FROM database
     */
    private Set<BrickDto> bricksDtos;
    
    @Digits(integer = 9, fraction = 2, message = "Price must have at most two fraction digits and nine digits.")
    @DecimalMin(value = "0.00", message = "Price can't be negative.")
    @NotNull(message = "Price must be filled")
    private BigDecimal price;

    @NotNull(message = "Currency must be selected")
    private Currency currency;
    
    @Min(value = 0, message = "Minimal age must be at least 0.")
    private short minAge;
    
    @Min(value = 0, message = "Maximal age must be at least 0.")
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

    public CategoryDto getCategoryDto() {
        return categoryDto;
    }

    public void setCategoryDto(CategoryDto categoryDto) {
        this.categoryDto = categoryDto;
    }

    public Set<Long> getBricksIds() {
        return bricksIds;
    }

    public void setBricksIds(Set<Long> bricksIds) {
        this.bricksIds = bricksIds;
    }

    public Set<BrickDto> getBricksDtos() {
        return bricksDtos;
    }

    public void setBricksDtos(Set<BrickDto> bricksDtos) {
        this.bricksDtos = bricksDtos;
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
