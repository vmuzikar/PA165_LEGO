package org.legomanager.web.validators;

import org.legomanager.api.dto.CategoryDto;
import org.legomanager.api.facade.CategoryFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validates unique name
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Component
public class CategoryValidator implements Validator {
    @Autowired
    private CategoryFacade categoryFacade;

    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CategoryDto categoryDto = (CategoryDto) o;
        if (categoryDto.getName() == null) {
            return;
        }
        CategoryDto categoryDtoFound = categoryFacade.getCategory(categoryDto.getName());
        if (categoryDtoFound != null && categoryDtoFound.getId() != categoryDto.getId()) {
            errors.rejectValue("name", "name.notvalid", "Name must be unique");
        }
    }
}
