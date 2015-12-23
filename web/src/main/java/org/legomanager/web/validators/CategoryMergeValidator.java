package org.legomanager.web.validators;

import org.legomanager.api.dto.CategoryDto;
import org.legomanager.api.dto.CategoryMergeDto;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * Validates merging conflicts
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Component
public class CategoryMergeValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return CategoryMergeDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        CategoryMergeDto categoryMergeDto = (CategoryMergeDto) o;
        if (categoryMergeDto.getTargetId() == null || categoryMergeDto.getMergeWithIds() == null) {
            return;
        }
        if (categoryMergeDto.getMergeWithIds().contains(categoryMergeDto.getTargetId())) {
            errors.rejectValue("targetId", "targeId.novalid", "You cannot merge category with itself!");
        }
    }
}
