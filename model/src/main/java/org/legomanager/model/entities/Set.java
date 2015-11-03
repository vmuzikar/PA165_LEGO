package org.legomanager.model.entities;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity of the set
 *
 * @author Michal Valeš <michal@vales.me>
 */
@Entity
@Table(name="set_tab")
public class Set implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    private java.util.Set<Category> categories = new HashSet<Category>();
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    private java.util.Set<Kit> kits = new HashSet<Kit>();

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Currency currency;

    @Column(nullable = false)
    private short minAge;

    @Column(nullable = false)
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
    
    public java.util.Set<Category> getCategories() {
        return Collections.unmodifiableSet(categories);
    }

    public void setCategories(java.util.Set<Category> categories) {
        this.categories = categories;
    }

    public void addCategory(Category category) {
        this.categories.add(category);
        category.addSet(this);
    }

    public java.util.Set<Kit> getKits() {
        return Collections.unmodifiableSet(kits);
    }

    public void setKits(java.util.Set<Kit> kits) {
        this.kits = kits;
    }

    public void addKit(Kit kit) {
        this.kits.add(kit);
        kit.addSet(this);
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
        return this == o || (o instanceof Set && ((Set) o).getName().equals(this.getName()));
    }

    @Override
    public int hashCode() {
        return this.getName() != null ? this.getName().hashCode() : 0;
    }
}
