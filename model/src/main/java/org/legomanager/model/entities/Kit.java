package org.legomanager.model.entities;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Currency;
import java.util.HashSet;
import java.util.Set;

/**
 * Representation of a kit
 * TODO: [?] Many-to-many not empty constraint [?]
 * TODO: [?] Bricks counts [?]
 * TODO: [?] Price constrains [?]
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Entity
public class Kit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    private Category category;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<Brick> bricks = new HashSet<Brick>();

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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
        category.addKit(this);
    }

    public Set<Brick> getBricks() {
        return Collections.unmodifiableSet(bricks);
    }

    public void addBrick(Brick brick) {
        bricks.add(brick);
        brick.addKit(this);
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
        if (!(o instanceof Kit)) {return false;}

        Kit kit = (Kit) o;

        return kit.getName() != null && getName() != null && kit.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
