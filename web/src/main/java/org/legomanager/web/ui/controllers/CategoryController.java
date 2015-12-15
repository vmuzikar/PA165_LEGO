package org.legomanager.web.ui.controllers;

import org.legomanager.api.dto.CategoryDto;
import org.legomanager.api.facade.CategoryFacade;
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
@RequestMapping(Urls.CATEGORY)
public class CategoryController {
    @Autowired
    private CategoryFacade categoryFacade;

    @RequestMapping(method = RequestMethod.GET)
    public String getAll(Model model) {
        model.addAttribute("categories", categoryFacade.getAllCategories());
        return "categories/list";
    }
    
    @RequestMapping(value = Urls.UNUSED, method = RequestMethod.GET)
    public String getUnused(Model model) {
        model.addAttribute("categories", categoryFacade.getUnusedCategories());
        return "categories/list";
    }
    
    @RequestMapping(value = Urls.MOST_USED + "/{amount}", method = RequestMethod.GET)
    public String getMostUsed(@PathVariable int amount, Model model) {
        model.addAttribute("categories", categoryFacade.getCategoriesWithMostKits(amount));
        return "categories/list";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String detail(@PathVariable long id, Model model) {
        CategoryDto categoryDto = categoryFacade.getCategory(id);
        if (categoryDto == null) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("category", categoryDto);

        return "categories/detail";
    }

    @RequestMapping(value = Urls.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("category", new CategoryDto());
        return "categories/form";
    }

    @RequestMapping(value = Urls.CREATE, method = RequestMethod.POST)
    public String createProcess(
            @Valid @ModelAttribute("category") CategoryDto categoryDto,
            BindingResult bindingResult, Model model
    )
    {
        long newId;
        if (!bindingResult.hasErrors()) {
            newId = categoryFacade.createCategory(categoryDto);
            return "redirect:" + newId;
        }
        else {
            return "categories/form";
        }
    }
}