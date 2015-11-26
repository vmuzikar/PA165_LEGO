package org.legomanager.service.services;

import org.legomanager.persistence.entities.Brick;
import java.util.List;

/**
 * Service for manipulation with bricks
 *
 * @author Michal Valeš <michal@vales.me>
 */
public interface BrickService extends AbstractBaseDaoService<Brick> {
    public List<Brick> getUnused();
    public List<Brick> getMostUsed(int count);
}
