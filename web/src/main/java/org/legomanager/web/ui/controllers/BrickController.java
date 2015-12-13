package org.legomanager.web.ui.controllers;

import org.legomanager.api.dto.BrickDto;
import org.legomanager.api.facade.BrickFacade;
import org.legomanager.web.Urls;
import org.legomanager.web.ui.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

/**
 * Controller for Bricks
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Controller
@RequestMapping(Urls.BRICK)
public class BrickController {
    @Autowired
    private BrickFacade brickFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("bricks", brickFacade.getAllBricks());
        return "bricks/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        BrickDto brickDto = brickFacade.getBrick(id);
        if (brickDto == null) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("brick", brickDto);

        return "bricks/detail";
    }

    @RequestMapping(value = Urls.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("brick", new BrickDto());
        return "bricks/form";
    }

    @RequestMapping(value = Urls.CREATE, method = RequestMethod.POST)
    public String createProcess(
            @Valid @ModelAttribute("brick") BrickDto brickDto,
            BindingResult bindingResult, Model model
    )
    {
        long newId;
        if (!bindingResult.hasErrors()) {
            newId = brickFacade.createBrick(brickDto);
            return "redirect:" + newId;
        }
        else {
            return "bricks/form";
        }
    }
}
