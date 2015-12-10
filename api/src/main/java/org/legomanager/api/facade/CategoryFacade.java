package org.legomanager.api.facade;

import org.legomanager.api.dto.BrickDto;
import org.legomanager.api.dto.CategoryDto;

import java.util.List;

/**
 * Facade for manipulation with categories
 *
 * @author Michal Vale� <michal@vales.me>
 */
public interface CategoryFacade {
    
    /**
     * Creates category. 
     *
     * @param categoryDto Category to be created.
     */
    long createCategory(CategoryDto categoryDto);

    void editCategory(CategoryDto categoryDto);
    
    /**
     * Deletes category. 
     *
     * @param id Category to be deleted.
     */
    void removeCategory(long id);

    CategoryDto getCategory(long id);

    /**
     * Fetches all categories. 
     *
     * @return List of all categories.
     */
    List<CategoryDto> getAllCategories();
    
    /**
     * Merges kits from all categories into target category, all except the 
     * target are deleted.
     *
     * @param targetId Category for merging into.
     * @param withIds List of categories to merge into target and delete.
     */
    void merge(long targetId, List<Long> withIds);
    
    /**
     * Fetches all categories which are not containing any kit. 
     *
     * @return List of all categories which are not containing any kit.
     */
    List<CategoryDto> getUnusedCategories();
    
    /**
     * Fetches categories sorted by number of contained kits. 
     *
     * @param count Number of categories to be fetched. Count has to be at least 1, 
     *              if it's less, IllegalArgumentException is thrown. When count 
     *              exceeds number of categories, all categories are fetched.
     * @return List of categories sorted by number of contained kits.
     */
    List<CategoryDto> getCategoriesWithMostKits(int count);
    
}
