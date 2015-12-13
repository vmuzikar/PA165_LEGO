package org.legomanager.web.restapi.resources;

import org.legomanager.api.dto.BrickDto;
import org.legomanager.web.restapi.controllers.BrickRestController;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Assembler for brick HATEOAS for REST API
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Component
public class BrickResourceAssembler extends ResourceAssemblerSupport<BrickDto, BrickResource> {
    public BrickResourceAssembler() {
        super(BrickRestController.class, BrickResource.class);
    }

    public BrickResource toResource(BrickDto brickDto) {
        BrickResource brickResource = new BrickResource(brickDto);

        Link selfLink = linkTo(methodOn(BrickRestController.class).getById(brickDto.getId())).withSelfRel();
        brickResource.add(selfLink);

        return brickResource;
    }
}
