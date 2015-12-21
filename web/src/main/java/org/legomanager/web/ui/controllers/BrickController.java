package org.legomanager.web.ui.controllers;

import org.legomanager.api.dto.BrickDto;
import org.legomanager.api.facade.BrickFacade;
import org.legomanager.web.Urls;
import org.legomanager.web.ui.exceptions.ResourceNotFoundException;
import org.legomanager.web.validators.BrickValidator;
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

    @Autowired
    private BrickValidator brickValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("bricks", brickFacade.getAllBricks());
        return "bricks/list";
    }
    
    @RequestMapping(value = Urls.UNUSED, method = RequestMethod.GET)
    public String getUnused(Model model) {
        model.addAttribute("bricks", brickFacade.getUnusedBricks());
        return "bricks/list";
    }
    
    @RequestMapping(value = Urls.MOST_USED + "/{amount}", method = RequestMethod.GET)
    public String getMostUsed(@PathVariable int amount, Model model) {
        model.addAttribute("bricks", brickFacade.getMostUsedBricks(amount));
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
    
    @RequestMapping(value = "/{id}" + Urls.DELETE, method = RequestMethod.GET)
    public String delete(@PathVariable long id, Model model) {
        if (brickFacade.getBrick(id) == null) {
            throw new ResourceNotFoundException();
        }
        brickFacade.removeBrick(id);
        return "redirect:/brick";
    }

    @RequestMapping(value = Urls.CREATE, method = RequestMethod.POST)
    public String createProcess(
            @Valid @ModelAttribute("brick") BrickDto brickDto,
            BindingResult bindingResult, Model model
        ) {
        brickValidator.validate(brickDto, bindingResult);
        if (!bindingResult.hasErrors()) {
            brickFacade.createBrick(brickDto);
            return "redirect:";
        }
        else {
            return "bricks/form";
        }
    }
}
