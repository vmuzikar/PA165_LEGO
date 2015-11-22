package org.legomanager.persistence.dao;

import org.legomanager.persistence.entities.Category;
import org.springframework.stereotype.Repository;

/**
 * This is base implementation of {@link CategoryDao}.
 * 
 * @author Michal Valeš <michal@vales.me>
 */
@Repository
public class CategoryDaoImpl extends AbstractBaseDaoImpl<Category> implements CategoryDao {
    public CategoryDaoImpl() {
        super(Category.class);
    }
}
