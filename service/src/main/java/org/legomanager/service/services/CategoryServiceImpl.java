package org.legomanager.service.services;

import org.legomanager.persistence.dao.CategoryDao;
import org.legomanager.persistence.entities.Category;
import org.legomanager.service.utils.SearchObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.legomanager.persistence.dao.AbstractBaseDao;
import org.legomanager.persistence.entities.Kit;

/**
 * Implementation of service for manipulation with kits
 *
 * @author Michal Vale� <michal@vales.me>
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Service
public class CategoryServiceImpl extends AbstractBaseDaoServiceImpl<Category> implements CategoryService {
    @Autowired
    private KitService kitService;

    @Autowired
    public CategoryServiceImpl(CategoryDao dao) {
        super(dao, Category.class);
    }
    
    public Category merge(long targetId, List<Long> withIds) {
        CategoryDao dao = (CategoryDao) getDao();

        Category result = dao.findById(targetId);
        if (result == null) {
            throw new DataRetrievalFailureException("Target category could not be found");

        }

        List<Category> with = new ArrayList<Category>();
        for (long id : withIds) {
            Category category = dao.findById(id);
            if (category == null) {
                throw new DataRetrievalFailureException("Source category could not be found");
            }
            with.add(category);
        }

        for (Category c : with){
            Set<Kit> kits = new HashSet<>(c.getKits());
            for (Kit k : kits){
                if (!k.getCategory().equals(result)) {
                    k.setCategory(result);
                    kitService.update(k);
                }
            }
            dao.delete(c);
        }
        dao.update(result);
        return result;
    }
    
    public List<Category> getUnused() {
        SearchCategory search = new SearchCategory(getDao());
        return search.getUnused();
    }
    
    public List<Category> getWithMostKits(int count) {
        SearchCategory search = new SearchCategory(getDao());
        return search.getMostUsed(count);
    }
    
    private class SearchCategory extends SearchObjects<Category> {

        public SearchCategory(AbstractBaseDao<Category> dao) {
            super(dao);
        }
        
        @Override
        protected Set<Kit> getKits(Category entity) {
            return entity.getKits();
        }
        
    }
}
