package org.legomanager.web;

import org.legomanager.api.dto.BrickDto;
import org.legomanager.api.dto.CategoryDto;
import org.legomanager.api.dto.KitDto;
import org.legomanager.api.facade.BrickFacade;
import org.legomanager.api.facade.CategoryFacade;
import org.legomanager.api.facade.KitFacade;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;

/**
 * Populates the DB with some default values
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Component
public class WebAppInitValues implements InitializingBean {
    @Autowired
    private BrickFacade brickFacade;

    @Autowired
    private CategoryFacade categoryFacade;

    @Autowired
    private KitFacade kitFacade;

    public void afterPropertiesSet() throws Exception {
        // Bricks
        BrickDto brickDto;

        brickDto = new BrickDto();
        brickDto.setName("Brick 2*2");
        brickDto.setWidth(2);
        brickDto.setHeight(2);
        long brick2_2 = brickFacade.createBrick(brickDto);

        brickDto = new BrickDto();
        brickDto.setName("Brick 2*4");
        brickDto.setWidth(4);
        brickDto.setHeight(2);
        long brick2_4 = brickFacade.createBrick(brickDto);

        brickDto = new BrickDto();
        brickDto.setName("Brick 2*6");
        brickDto.setWidth(6);
        brickDto.setHeight(2);
        long brick2_6 = brickFacade.createBrick(brickDto);

        brickDto = new BrickDto();
        brickDto.setName("Brick 10*6");
        brickDto.setWidth(6);
        brickDto.setHeight(10);
        long brick10_6 = brickFacade.createBrick(brickDto);


        // Categories
        CategoryDto categoryDto;

        categoryDto = new CategoryDto();
        categoryDto.setName("Star Wars");
        long categSW = categoryFacade.createCategory(categoryDto);

        categoryDto = new CategoryDto();
        categoryDto.setName("Star Trek");
        long categST = categoryFacade.createCategory(categoryDto);

        categoryDto = new CategoryDto();
        categoryDto.setName("Hello Kitty");
        long categHelloKitty = categoryFacade.createCategory(categoryDto);


        // Kits
        KitDto kitDto;

        kitDto = new KitDto();
        kitDto.setName("Star Wars: The Force Awakens");
        kitDto.setCategoryId(categSW);
        kitDto.setMinAge((short) 16);
        kitDto.setMaxAge((short) 120);
        kitDto.setCurrency(Currency.getInstance("CZK"));
        kitDto.setPrice(new BigDecimal("5499"));
        kitDto.setBricksIds(new HashSet<>(Arrays.asList(brick2_2, brick2_4, brick2_6)));
        kitFacade.createKit(kitDto);

        kitDto = new KitDto();
        kitDto.setName("Star Wars Ultimate Starter Pack");
        kitDto.setCategoryId(categSW);
        kitDto.setMinAge((short) 1);
        kitDto.setMaxAge((short) 6);
        kitDto.setCurrency(Currency.getInstance("CZK"));
        kitDto.setPrice(new BigDecimal("1099"));
        kitDto.setBricksIds(new HashSet<>(Arrays.asList(brick2_2)));
        kitFacade.createKit(kitDto);

        kitDto = new KitDto();
        kitDto.setName("Star Trek Voyager For Seniors");
        kitDto.setCategoryId(categST);
        kitDto.setMinAge((short) 80);
        kitDto.setMaxAge((short) 149);
        kitDto.setCurrency(Currency.getInstance("EUR"));
        kitDto.setPrice(new BigDecimal("199"));
        kitDto.setBricksIds(new HashSet<>(Arrays.asList(brick2_2, brick2_4)));
        kitFacade.createKit(kitDto);
    }
}
