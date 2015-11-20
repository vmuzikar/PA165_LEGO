package org.legomanager.persistence.entities;

import java.util.Collections;
import java.util.HashSet;
import java.io.Serializable;
import javax.persistence.*;

/**
 * Entity of the category
 *
 * @author Michal Valeš <michal@vales.me>
 */
@Entity
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;
    
    @OneToMany(mappedBy = "category")
    private java.util.Set<Kit> kits = new HashSet<Kit>();

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

    public java.util.Set<Kit> getKits() {
        return Collections.unmodifiableSet(kits);
    }

    public void addKit(Kit kit) {
        this.kits.add(kit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Category)) {
            return false;
        }
        Category c = (Category) o;
        return (c.getName() != null && this.getName() != null && this.getName().equals(c.getName()));
    }

    @Override
    public int hashCode() {
        return (this.getName() != null ? this.getName().hashCode() : 0);
    }
}
