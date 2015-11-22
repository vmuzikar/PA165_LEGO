package org.legomanager.service.facade;

import org.legomanager.api.dto.KitDto;
import org.legomanager.persistence.entities.Kit;
import org.legomanager.service.services.BeanMappingService;
import org.legomanager.service.services.KitService;
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
    BeanMappingService mappingService;

    public void createKit(KitDto kitDto) {
        Kit kit = mappingService.map(kitDto, Kit.class);
        kitService.create(kit);
    }

    public void removeKit(KitDto kitDto) {
        Kit kit = mappingService.map(kitDto, Kit.class);
        kitService.remove(kit);
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
}
