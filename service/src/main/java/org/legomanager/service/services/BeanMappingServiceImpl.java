package org.legomanager.service.services;

import org.dozer.Mapper;
import org.legomanager.api.dto.KitDto;
import org.legomanager.persistence.entities.Brick;
import org.legomanager.persistence.entities.Kit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

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
        for (Brick brick : kit.getBricks()) {
            kitDto.getBricksIds().add(brick.getId());
        }

        kitDto.setCategoryId(kit.getCategory().getId());
    }

    private void kitDtoToKit(KitDto kitDto, Kit kit) {
        kit.removeAllBricks();
        for (Long brickId : kitDto.getBricksIds()) {
            kit.addBrick(brickService.findById(brickId));
        }

        kit.setCategory(categoryService.findById(kitDto.getCategoryId()));
    }
}
