package org.legomanager.model.dao;

import org.legomanager.model.entities.Set;

import java.util.List;

/**
 * @author Michal Valeš <michal@vales.me>
 */
public interface SetDao {
    Set findById(long id);
    void create(Set set);
    void delete(Set set);
    void update(Set set);
    List<Set> findAll();
}
