package org.legomanager.service.services;

import org.legomanager.persistence.entities.Category;
import java.util.List;

/**
 * Service for manipulation with bricks
 *
 * @author Michal Vale� <michal@vales.me>
 */
public interface CategoryService extends AbstractBaseDaoService<Category> {
    public Category merge(long targetId, List<Long> withIds);
    public List<Category> getUnused();
    public List<Category> getWithMostKits(int count);
}
