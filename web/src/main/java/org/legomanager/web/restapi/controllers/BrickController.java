package org.legomanager.web.restapi.controllers;

import org.legomanager.api.dto.BrickDto;
import org.legomanager.api.facade.BrickFacade;
import org.legomanager.web.Urls;
import org.legomanager.web.restapi.resources.BrickResource;
import org.legomanager.web.restapi.resources.BrickResourceAssembler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

/**
 * Brick REST API
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@RestController
@RequestMapping(Urls.REST_ROOT + Urls.BRICK)
public class BrickController {
    @Autowired
    private BrickFacade brickFacade;

    @Autowired
    private BrickResourceAssembler brickResourceAssembler;

    /**
     * Gets all Bricks
     * @return all bricks
     */
    @RequestMapping(method = RequestMethod.GET)
    public HttpEntity<Resources<BrickResource>> getAll() {
        List<BrickDto> bricksDtos = brickFacade.getAllBricks();

        List<BrickResource> bricksResourcesList = brickResourceAssembler.toResources(bricksDtos);
        Resources<BrickResource> bricksResources = new Resources<>(bricksResourcesList);

        bricksResources.add(linkTo(methodOn(BrickController.class).create(null)).withRel("create"));

        return new ResponseEntity<>(bricksResources, HttpStatus.OK);
    }

    /**
     * Gets one specific Brick
     * @param id of a brick
     * @return brick or "404 Not Found"
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public HttpEntity<BrickResource> getById(@PathVariable("id") long id) {
        BrickDto brickDto = brickFacade.getBrick(id);
        if (brickDto == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        BrickResource brickResource = brickResourceAssembler.toResource(brickDto);
        return new ResponseEntity<>(brickResource, HttpStatus.OK);
    }

    /**
     * Creates a Brick
     * @param brickDto provided by JSON
     * @return the created Brick or "409 Conflict if there was a constraint violation"
     */
    @RequestMapping(value = Urls.REST_CREATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<BrickResource> create(@RequestBody BrickDto brickDto) {
        long id;
        try {
            id = brickFacade.createBrick(brickDto);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return getById(id);
    }

    /**
     * Deletes a Brick
     * @param id of the Brick
     * @return "200 OK" or "404 Not Found"
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public HttpEntity delete(@PathVariable("id") long id) {
        try {
            brickFacade.removeBrick(id);
        }
        catch (Exception e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    /**
     * Edits a Brick
     * @param id of the Brick
     * @param brickDto complete specification (not changed attributes included) of a Brick provided by JSON
     * @return the edited Brick or "404 Not Found" or "409 Conflict" if there was a constraint violation
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public HttpEntity<BrickResource> edit(
            @PathVariable("id") long id,
            @RequestBody BrickDto brickDto
    )
    {
        BrickDto brickDtoFound = brickFacade.getBrick(id);
        if (brickDtoFound == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        brickDto.setId(id);
        try {
            brickFacade.editBrick(brickDto);
        }
        catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return getById(id);
    }
}
