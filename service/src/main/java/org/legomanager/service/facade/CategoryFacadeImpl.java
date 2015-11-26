package org.legomanager.service.facade;

import java.util.List;
import org.legomanager.api.dto.CategoryDto;
import org.legomanager.persistence.entities.Category;
import org.legomanager.api.facade.CategoryFacade;
import org.legomanager.service.services.BeanMappingService;
import org.legomanager.service.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of facade for manipulation with categories
 *
 * @author Michal Valeš <michal@vales.me>
 */
@Service
@Transactional
public class CategoryFacadeImpl implements CategoryFacade {
    
    @Autowired
    CategoryService categoryService;

    @Autowired
    BeanMappingService mappingService;

    public void createCategory(CategoryDto categoryDto) {
        Category category = mappingService.map(categoryDto, Category.class);
        categoryService.create(category);
    }

    public void removeCategory(CategoryDto categoryDto) {
        Category category = mappingService.map(categoryDto, Category.class);
        categoryService.remove(category);
    }

    public List<CategoryDto> getAllCategories() {
        return (List<CategoryDto>) mappingService.map(categoryService.findAll(), CategoryDto.class);
    }

    public List<CategoryDto> getUnusedCategories() {
        return (List<CategoryDto>) mappingService.map(categoryService.getUnused(), CategoryDto.class);
    }

    public List<CategoryDto> getCategoriesWithMostKits(int count) {
        return (List<CategoryDto>) mappingService.map(categoryService.getWithMostKits(count), CategoryDto.class);
    }
}
