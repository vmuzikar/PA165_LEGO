package org.legomanager.persistence.dao;

import org.legomanager.persistence.entities.Kit;

import java.util.List;

/**
 * DAO for Kit
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface KitDao extends AbstractBaseDao<Kit> {
    List<Kit> getKitsWithAgeRange(short ageFrom, short ageTo);
}
