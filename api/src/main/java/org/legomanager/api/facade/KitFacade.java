package org.legomanager.api.facade;

import org.legomanager.api.dto.KitDto;
import org.legomanager.api.representantions.ModelRepresentation;

import java.util.List;

/**
 * Interface for facade for manipulation with kits
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface KitFacade {
    long createKit(KitDto kitDto);
    void editKit(KitDto kitDto);
    void removeKit(long id);
    KitDto getKit(long id);
    List<KitDto> getAllKits();
    List<KitDto> getKitsForKids();
    List<KitDto> getKitsForTeenage();
    List<KitDto> getKitsForAdults();

    /**
     * Converts and imports defined model as a kit
     *
     * @param model a 3D model representation
     * @param kit a Kit with predefined values (name, cost, ...)
     */
    public void importModel(ModelRepresentation model, KitDto kit);
}
