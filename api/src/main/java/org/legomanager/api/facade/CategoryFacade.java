package org.legomanager.api.facade;

import org.legomanager.api.dto.CategoryDto;

import java.util.List;

/**
 * Facade for manipulation with categories
 *
 * @author Michal Valeš <michal@vales.me>
 */
public interface CategoryFacade {
    
    /**
     * Creates category. 
     *
     * @param categoryDto Category to be created.
     */
    void createCategory(CategoryDto categoryDto);
    
    /**
     * Deletes category. 
     *
     * @param categoryDto Category to be deleted.
     */
    void removeCategory(CategoryDto categoryDto);
    
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
     * @param targetDto Category for merging into.
     * @param withDto List of categories to merge into target and delete.
     * @return Merged category.
     */
    CategoryDto merge(CategoryDto targetDto, List<CategoryDto> withDto);
    
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
