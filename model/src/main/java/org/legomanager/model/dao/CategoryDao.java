package org.legomanager.model.dao;

import org.legomanager.model.entities.Category;
import java.util.List;

/**
 * Kits and sets are grouped into containing theme category like LEGO City, 
 * LEGO Star Wars, etc.
 * 
 * @author Michal Valeš <michal@vales.me>
 */
public interface CategoryDao {
    
    /**
     * Fetches category from database.
     *
     * @param id id of desired category
     * @return category found
     */
    Category findById(long id);
    
    /**
     * Pushes category into database.
     *
     * @param category category to be inserted
     */
    void create(Category category);
    
    /**
     * Removes category from database.
     *
     * @param category category to be deleted
     */
    void delete(Category category);
    
    /**
     * Updates category in database.
     *
     * @param category category to be updated
     */
    void update(Category category);
    
    /**
     * Fetches all categories from database.
     *
     * @return list of found categories
     */
    List<Category> findAll();
}
