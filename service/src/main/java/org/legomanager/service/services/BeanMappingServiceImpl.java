package org.legomanager.service.services;

import org.dozer.Mapper;
import org.legomanager.api.dto.BrickDto;
import org.legomanager.api.dto.CategoryDto;
import org.legomanager.api.dto.KitDto;
import org.legomanager.api.exceptions.ServiceException;
import org.legomanager.persistence.entities.Brick;
import org.legomanager.persistence.entities.Category;
import org.legomanager.persistence.entities.Kit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Service for bean mapping (i.e. conversion between an entity and a DTO)
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {
    @Autowired
    private Mapper mapper;

    @Autowired
    private BrickService brickService;

    @Autowired
    private CategoryService categoryService;

    public <T> T map(Object obj, Class<T> mapTo) {
        if (obj == null) {
            return null;
        }

        T ret = mapper.map(obj, mapTo);

        if (obj instanceof Kit && mapTo == KitDto.class) {
            kitToKitDto((Kit) obj, (KitDto) ret);
        }

        if (obj instanceof KitDto && mapTo == Kit.class) {
            kitDtoToKit((KitDto) obj, (Kit) ret);
        }

        return ret;
    }

    public <T> Collection<T> map(Collection<?> objs, Class<T> mapTo) {
        List<T> ret = new ArrayList<T>();
        for (Object obj : objs) {
            ret.add(map(obj, mapTo));
        }
        return ret;
    }

    private void kitToKitDto(Kit kit, KitDto kitDto) {
        kitDto.setBricksIds(new HashSet<>());
        kitDto.setBricksDtos(new HashSet<>());
        for (Brick brick : kit.getBricks()) {
            kitDto.getBricksIds().add(brick.getId());
            kitDto.getBricksDtos().add(mapper.map(brick, BrickDto.class));
        }

        kitDto.setCategoryId(kit.getCategory().getId());
        kitDto.setCategoryDto(mapper.map(kit.getCategory(), CategoryDto.class));
    }

    private void kitDtoToKit(KitDto kitDto, Kit kit) {
        kit.removeAllBricks();
        for (Long brickId : kitDto.getBricksIds()) {
            Brick brick = brickService.findById(brickId);
            if (brick == null) {
                throw new ServiceException("Cannot convert id to Brick - not found!");
            }
            kit.addBrick(brick);
        }

        Category category = categoryService.findById(kitDto.getCategoryId());
        if (category == null) {
            throw new ServiceException("Cannot convert id to Category - not found!");
        }
        kit.setCategory(category);
    }
}
