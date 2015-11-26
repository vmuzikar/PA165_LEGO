package org.legomanager.service.services;

import org.legomanager.persistence.entities.Category;
import java.util.List;

/**
 * Service for manipulation with bricks
 *
 * @author Michal Valeš <michal@vales.me>
 */
public interface CategoryService extends AbstractBaseDaoService<Category> {
    public List<Category> getUnused();
    public List<Category> getWithMostKits(int count);
}
