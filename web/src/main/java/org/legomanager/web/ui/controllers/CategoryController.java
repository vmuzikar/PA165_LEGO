package org.legomanager.web.ui.controllers;

import org.legomanager.api.dto.CategoryDto;
import org.legomanager.api.dto.CategoryMergeDto;
import org.legomanager.api.facade.CategoryFacade;
import org.legomanager.web.Urls;
import org.legomanager.web.ui.exceptions.ResourceNotFoundException;
import org.legomanager.web.validators.CategoryMergeValidator;
import org.legomanager.web.validators.CategoryValidator;
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
 * Controller for Bricks
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Controller
@RequestMapping(Urls.CATEGORY)
public class CategoryController {
    @Autowired
    private CategoryFacade categoryFacade;

    @Autowired
    private CategoryValidator categoryValidator;

    @Autowired
    private CategoryMergeValidator categoryMergeValidator;

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

    @RequestMapping(value = "/{id}" + Urls.DELETE, method = RequestMethod.GET)
    public String delete(@PathVariable long id, Model model) {
        if (categoryFacade.getCategory(id) == null) {
            throw new ResourceNotFoundException();
        }
        categoryFacade.removeCategory(id);
        return "redirect:/category";
    }

    @RequestMapping(value = Urls.CREATE, method = RequestMethod.GET)
    public String create(Model model) {
        model.addAttribute("category", new CategoryDto());
        model.addAttribute("create", true);
        return "categories/form";
    }

    @RequestMapping(value = Urls.CREATE, method = RequestMethod.POST)
    public String createProcess(
            @Valid @ModelAttribute("category") CategoryDto categoryDto,
            BindingResult bindingResult, Model model
    )
    {
        categoryValidator.validate(categoryDto, bindingResult);
        if (!bindingResult.hasErrors()) {
            categoryFacade.createCategory(categoryDto);
            return "redirect:";
        }
        else {
            return "categories/form";
        }
    }

    @RequestMapping(value = "/{id}" + Urls.EDIT, method = RequestMethod.GET)
    public String edit(@PathVariable long id, Model model) {
        CategoryDto categoryDto = categoryFacade.getCategory(id);
        if (categoryDto == null) {
            throw new ResourceNotFoundException();
        }

        model.addAttribute("category", categoryDto);

        return "categories/form";
    }

    @RequestMapping(value = "/{id}" + Urls.EDIT, method = RequestMethod.POST)
    public String editProcess(
            @PathVariable long id,
            @Valid @ModelAttribute("category") CategoryDto categoryDto,
            BindingResult bindingResult
    ) {
        categoryDto.setId(id);
        categoryValidator.validate(categoryDto, bindingResult);
        if (!bindingResult.hasErrors()) {
            categoryFacade.editCategory(categoryDto);
            return "redirect:";
        }
        else {
            return "categories/form";
        }
    }

    @RequestMapping(value = Urls.MERGE, method = RequestMethod.GET)
    public String merge(Model model) {
        model.addAttribute("categoryMerge", new CategoryMergeDto());
        return "categories/merge";
    }

    @RequestMapping(value = Urls.MERGE, method = RequestMethod.POST)
    public String mergeProcess(
            @Valid @ModelAttribute("categoryMerge") CategoryMergeDto categoryMergeDto,
            BindingResult bindingResult
    ) {

        categoryMergeValidator.validate(categoryMergeDto, bindingResult);

        if (!bindingResult.hasErrors()) {
            categoryFacade.merge(categoryMergeDto.getTargetId(), categoryMergeDto.getMergeWithIds());
            return "redirect:";
        }
        else {
            return "categories/merge";
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
}
