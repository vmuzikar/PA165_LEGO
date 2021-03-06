package org.legomanager.persistence.entities;

import javax.persistence.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Representation of a single LEGO brick
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

    /**
     * As the stubs count
     */
    @Column(nullable = false)
    private int width;

    /**
     * As the stubs count
     */
    @Column(nullable = false)
    private int height;

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

    public Set<Kit> getKits() {
        return Collections.unmodifiableSet(kits);
    }

    public void addKit(Kit kit) {
        kits.add(kit);
    }

    public void removeKit(Kit kit) {
        kits.remove(kit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof Brick)) {return false;}

        Brick brick = (Brick) o;

        return brick.getName() != null && getName() != null && brick.getName().equals(getName());
    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
