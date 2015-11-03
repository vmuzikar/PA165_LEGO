package org.legomanager.model.dao;

import org.legomanager.model.entities.Category;

import java.util.List;

/**
 * @author Michal Valeš <michal@vales.me>
 */
public interface CategoryDao {
    Category findById(long id);
    void create(Category category);
    void delete(Category category);
    void update(Category category);
    List<Category> findAll();
}
