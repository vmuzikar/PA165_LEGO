package org.legomanager.persistence.dao;

import org.legomanager.persistence.entities.Brick;
import org.legomanager.persistence.entities.Kit;
import org.springframework.stereotype.Repository;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.HashSet;
import java.util.Set;

/**
 * DAO for Brick
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Repository
public class BrickDaoImpl extends AbstractBaseDaoImpl<Brick> implements BrickDao {
    public BrickDaoImpl() {
        super(Brick.class);
    }

    @Override
    public void delete(Brick entity) {
        Set<Kit> kits = new HashSet<>(entity.getKits());
        for (Kit kit : kits) {
            kit.removeBrick(entity);
        }
        super.delete(entity);
    }

    @Override
    public Brick getLargestBrick(int maxSize1, int maxSize2) {
        TypedQuery<Brick> query = em.createQuery(
                "SELECT b " +
                "FROM Brick b " +
                "WHERE " +
                    "(b.width <= :maxSize1 AND b.height <= :maxSize2) OR " +
                    "(b.height <= :maxSize1 AND b.width <= :maxSize2) " +
                "ORDER BY (b.width * b.height) DESC"
                , Brick.class);
        query.setMaxResults(1);
        query.setParameter("maxSize1", maxSize1);
        query.setParameter("maxSize2", maxSize2);

        try {
            return query.getSingleResult();
        }
        catch (NoResultException e) {
            return null;
        }
    }
}
