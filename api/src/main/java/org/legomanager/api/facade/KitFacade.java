package org.legomanager.api.facade;

import org.legomanager.api.dto.KitDto;

import java.util.List;

/**
 * Interface for facade for manipulation with kits
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface KitFacade {
    void createKit(KitDto kitDto);
    void removeKit(KitDto kitDto);
    List<KitDto> getAllKits();
    List<KitDto> getKitsForKids();
    List<KitDto> getKitsForTeenage();
    List<KitDto> getKitsForAdults();
}
