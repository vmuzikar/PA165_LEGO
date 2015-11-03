package org.legomanager.model.entities;

import org.legomanager.model.entities.converters.ColorConverter;

import javax.persistence.*;
import java.awt.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Representation of a color of a single LEGO brick
 * TODO: [?] Many-to-many not empty constraint [?]
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Entity
public class BrickColor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    @Convert(converter = ColorConverter.class)
    private Color color;

    @ManyToMany(mappedBy = "colors")
    private Set<Brick> bricks = new HashSet<Brick>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Set<Brick> getBricks() {
        return Collections.unmodifiableSet(bricks);
    }

    public void addBrick(Brick brick) {
        bricks.add(brick);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (!(o instanceof BrickColor)) {return false;}

        BrickColor color = (BrickColor) o;

        return color.getColor() != null && getColor() != null && color.getColor().equals(getColor());
    }

    @Override
    public int hashCode() {
        return getColor() != null ? getColor().hashCode() : 0;
    }
}
