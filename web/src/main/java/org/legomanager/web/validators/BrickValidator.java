package org.legomanager.web.validators;

import org.legomanager.api.dto.BrickDto;
import org.legomanager.api.facade.BrickFacade;
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
public class BrickValidator implements Validator {
    @Autowired
    private BrickFacade brickFacade;

    @Override
    public boolean supports(Class<?> aClass) {
        return BrickDto.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        BrickDto brickDto = (BrickDto) o;
        if (brickDto.getName() == null) {
            return;
        }
        BrickDto brickDtoFound = brickFacade.getBrick(brickDto.getName());
        if (brickDtoFound != null && brickDtoFound.getId() != brickDto.getId()) {
            errors.rejectValue("name", "name.notvalid", "Name must be unique");
        }
    }
}
