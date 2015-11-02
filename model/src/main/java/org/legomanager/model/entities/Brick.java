package org.legomanager.model.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Representation of a single LEGO brick
 * TODO: [?] Many-to-many not empty constraint [?]
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Entity
public class Brick {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.PERSIST)
    private Set<BrickColor> colors = new HashSet<BrickColor>();

    @ManyToMany(mappedBy = "bricks")
    private Set<Kit> kits = new HashSet<Kit>();

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

    public Set<BrickColor> getColors() {
        return Collections.unmodifiableSet(colors);
    }

    public void addColor(BrickColor color) {
        colors.add(color);
        color.addBrick(this);
    }

    @Override
    public boolean equals(Object o) {
        return this == o
                || (o instanceof Brick && ((Brick) o).getName().equals(getName()));
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}