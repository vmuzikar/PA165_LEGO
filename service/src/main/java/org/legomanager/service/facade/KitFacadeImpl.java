package org.legomanager.service.facade;

import org.legomanager.api.dto.KitDto;
import org.legomanager.api.representantions.ModelRepresentation;
import org.legomanager.persistence.entities.Brick;
import org.legomanager.persistence.entities.Kit;
import org.legomanager.service.services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Facade for manipulation with kits
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Service
@Transactional
public class KitFacadeImpl implements org.legomanager.api.facade.KitFacade {
    @Autowired
    KitService kitService;

    @Autowired
    BrickService brickService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    BeanMappingService mappingService;

    @Autowired
    ModelKitConverterService modelKitConverterService;

    public long createKit(KitDto kitDto) {
        Kit kit = mappingService.map(kitDto, Kit.class);
        for (Long brickId : kitDto.getBricksIds()) {
            kit.addBrick(brickService.findById(brickId));
        }
        kit.setCategory(categoryService.findById(kitDto.getCategoryId()));
        kitService.create(kit);
        return kit.getId();
    }

    public void editKit(KitDto kitDto) {
        Kit kit = mappingService.map(kitDto, Kit.class);
        kitService.update(kit);
    }

    public void removeKit(long id) {
        Kit kit = kitService.findById(id);
        kitService.remove(kit);
    }

    public KitDto getKit(long id) {
        Kit kit = kitService.findById(id);
        KitDto kitDto = mappingService.map(kit, KitDto.class);
        kitDto.setCategoryId(kit.getCategory().getId());
        return kitDto;
    }

    public List<KitDto> getAllKits() {
        return (List<KitDto>) mappingService.map(kitService.findAll(), KitDto.class);
    }

    public List<KitDto> getKitsForKids() {
        return (List<KitDto>) mappingService.map(kitService.getKitsForKids(), KitDto.class);
    }

    public List<KitDto> getKitsForTeenage() {
        return (List<KitDto>) mappingService.map(kitService.getKitsForTeenage(), KitDto.class);
    }

    public List<KitDto> getKitsForAdults() {
        return (List<KitDto>) mappingService.map(kitService.getKitsForAdults(), KitDto.class);
    }

    public void importModel(ModelRepresentation model, KitDto kitDto) {
        Kit convertedKit = modelKitConverterService.convertModelToKit(model);
        Kit kit = mappingService.map(kitDto, Kit.class);

        for (Brick brick : convertedKit.getBricks()) {
            kit.addBrick(brick);
        }

        kitService.create(kit);
    }
}
