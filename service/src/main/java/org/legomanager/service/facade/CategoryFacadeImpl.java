package org.legomanager.service.facade;

import java.util.List;
import org.legomanager.api.dto.CategoryDto;
import org.legomanager.persistence.entities.Category;
import org.legomanager.api.facade.CategoryFacade;
import org.legomanager.service.services.BeanMappingService;
import org.legomanager.service.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.LocaleEditor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implementation of facade for manipulation with categories
 *
 * @author Michal Vale� <michal@vales.me>
 */
@Service
@Transactional
public class CategoryFacadeImpl implements CategoryFacade {
    
    @Autowired
    CategoryService categoryService;

    @Autowired
    BeanMappingService mappingService;

    public long createCategory(CategoryDto categoryDto) {
        Category category = mappingService.map(categoryDto, Category.class);
        categoryService.create(category);
        return category.getId();
    }

    public void editCategory(CategoryDto categoryDto) {
        Category category = mappingService.map(categoryDto, Category.class);
        categoryService.update(category);
    }

    public void removeCategory(long id) {
        Category category = categoryService.findById(id);
        categoryService.remove(category);
    }

    public CategoryDto getCategory(long id) {
        return mappingService.map(categoryService.findById(id), CategoryDto.class);
    }

    public CategoryDto getCategory(String name) {
        return mappingService.map(categoryService.findByName(name), CategoryDto.class);
    }

    public List<CategoryDto> getAllCategories() {
        return (List<CategoryDto>) mappingService.map(categoryService.findAll(), CategoryDto.class);
    }

    public void merge(long targetId, List<Long> withIds) {
        categoryService.merge(targetId, withIds);
    }

    public List<CategoryDto> getUnusedCategories() {
        return (List<CategoryDto>) mappingService.map(categoryService.getUnused(), CategoryDto.class);
    }

    public List<CategoryDto> getCategoriesWithMostKits(int count) {
        return (List<CategoryDto>) mappingService.map(categoryService.getWithMostKits(count), CategoryDto.class);
    }
}
