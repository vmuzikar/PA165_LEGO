package org.legomanager.service.services;

import org.legomanager.persistence.entities.Kit;

import java.util.List;

/**
 * Interface of service for manipulation with kits
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface KitService extends AbstractBaseDaoService<Kit> {
    public List<Kit> getKitsForKids();
    public List<Kit> getKitsForTeenage();
    public List<Kit> getKitsForAdults();
}
