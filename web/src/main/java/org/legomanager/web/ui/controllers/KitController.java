package org.legomanager.web.ui.controllers;

import org.legomanager.api.dto.BrickDto;
import org.legomanager.api.dto.CategoryDto;
import org.legomanager.api.dto.KitDto;
import org.legomanager.api.facade.BrickFacade;
import org.legomanager.api.facade.CategoryFacade;
import org.legomanager.api.facade.KitFacade;
import org.legomanager.web.Urls;
import org.legomanager.web.ui.exceptions.ResourceNotFoundException;
import org.legomanager.web.validators.KitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Controller for Kits
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Controller
@RequestMapping(Urls.KIT)
public class KitController {
    @Autowired
    private KitFacade kitFacade;

    @Autowired
    private CategoryFacade categoryFacade;

    @Autowired
    private BrickFacade brickFacade;

    @Autowired
    private KitValidator kitValidator;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("kits", kitFacade.getAllKits());
        return "kits/list";
    }

    @RequestMapping(value = Urls.FOR_KIDS, method = RequestMethod.GET)
    public String getForKids(Model model) {
        model.addAttribute("kits", kitFacade.getKitsForKids());
        return "kits/list";
    }

    @RequestMapping(value = Urls.FOR_TEENAGE, method = RequestMethod.GET)
    public String getForTeenage(Model model) {
        model.addAttribute("kits", kitFacade.getKitsForTeenage());
        return "kits/list";
    }

    @RequestMapping(value = Urls.FOR_ADULTS, method = RequestMethod.GET)
    public String getForAdults(Model model) {
        model.addAttribute("kits", kitFacade.getKitsForAdults());
        return "kits/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        KitDto kitDto = kitFacade.getKit(id);
        if (kitDto == null) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("kit", kitDto);

        return "kits/detail";
    }
    
    @RequestMapping(value = "/{id}" + Urls.DELETE, method = RequestMethod.GET)
    public String delete(@PathVariable long id, Model model) {
        if (kitFacade.getKit(id) == null) {
            throw new ResourceNotFoundException();
        }
        kitFacade.removeKit(id);
        return "redirect:/kit";
    }

    @RequestMapping(value = Urls.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("kit", new KitDto());
        model.addAttribute("create", true);
        return "kits/form";
    }

    @RequestMapping(value = Urls.CREATE, method = RequestMethod.POST)
    public String createProcess(
        @Valid @ModelAttribute("kit") KitDto kitDto,
        BindingResult bindingResult
    ) {
        kitValidator.validate(kitDto, bindingResult);
        if (!bindingResult.hasErrors()) {
            kitFacade.createKit(kitDto);
            return "redirect:";
        }
        else {
            return "kits/form";
        }
    }

    @RequestMapping(value = "/{id}" + Urls.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        KitDto kitDto = kitFacade.getKit(id);
        if (kitDto == null) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("kit", kitDto);

        return "kits/form";
    }

    @RequestMapping(value = "/{id}" + Urls.EDIT, method = RequestMethod.POST)
    public String editProcess(
            @PathVariable long id,
            @Valid @ModelAttribute("kit") KitDto kitDto,
            BindingResult bindingResult
    ) {
        kitDto.setId(id);
        kitValidator.validate(kitDto, bindingResult);
        if (!bindingResult.hasErrors()) {
            kitFacade.editKit(kitDto);
            return "redirect:";
        }
        else {
            return "kits/form";
        }
    }

    @ModelAttribute("categoriesMap")
    public Map<Long, String> allCategsMap() {
        List<CategoryDto> allCategsList = categoryFacade.getAllCategories();
        Map<Long, String> allCategsMap = new HashMap<>();

        for (CategoryDto category : allCategsList) {
            allCategsMap.put(category.getId(), category.getName());
        }

        return allCategsMap;
    }

    @ModelAttribute("bricksMap")
    public Map<Long, String> allBricksMap() {
        List<BrickDto> allBricksList = brickFacade.getAllBricks();
        Map<Long, String> allBricksMap = new HashMap<>();

        for (BrickDto brick : allBricksList) {
            allBricksMap.put(brick.getId(), brick.getName());
        }

        return allBricksMap;
    }

    @ModelAttribute("currenciesMap")
    public Map<String, String> currenciesMap() {
        Map<String, String> currencies = new HashMap<>();
        currencies.put("EUR", "EUR");
        currencies.put("CZK", "CZK");
        return currencies;
    }
}
