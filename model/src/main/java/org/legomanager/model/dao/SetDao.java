package org.legomanager.model.dao;

import org.legomanager.model.entities.Set;
import java.util.List;

/**
 * Kits can be grouped into sets as thematic series or special sale packages.
 * 
 * @author Michal Valeš <michal@vales.me>
 */
public interface SetDao {
    
    /**
     * Fetches set from database.
     *
     * @param id id of desired set
     * @return set found
     */
    Set findById(long id);
    
    /**
     * Pushes set into database.
     *
     * @param set set to be inserted
     */
    void create(Set set);
    
    /**
     * Removes set from database.
     *
     * @param set set to be deleted
     */
    void delete(Set set);
    
    /**
     * Updates set in database.
     *
     * @param set set to be updated
     */
    void update(Set set);
    
    /**
     * Fetches all sets from database.
     *
     * @return list of found sets
     */
    List<Set> findAll();
}
