package org.legomanager.web.validators;

import org.legomanager.api.dto.KitDto;
import org.legomanager.api.facade.KitFacade;
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
public class KitValidator implements Validator {
    @Autowired
    private KitFacade kitFacade;

    @Override
    public boolean supports(Class<?> aClass) {
        return KitDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        KitDto kitDto = (KitDto) o;

        if (kitDto.getMinAge() >= kitDto.getMaxAge()) {
            errors.rejectValue("maxAge", "maxAge.notvalid", "Minimal age must be greater than maximal age");
        }

        if (kitDto.getName() != null) {
            KitDto kitDtoFound = kitFacade.getKit(kitDto.getName());
            if (kitDtoFound != null && kitDtoFound.getId() != kitDto.getId()) {
                errors.rejectValue("name", "name.notvalid", "Name must be unique");
            }
        }
    }
}
