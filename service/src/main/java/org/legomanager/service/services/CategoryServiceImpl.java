package org.legomanager.service.services;

import org.legomanager.persistence.dao.CategoryDao;
import org.legomanager.persistence.entities.Category;
import org.legomanager.service.utils.SearchObjects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Set;
import org.legomanager.persistence.dao.AbstractBaseDao;
import org.legomanager.persistence.entities.Kit;

/**
 * Implementation of service for manipulation with kits
 *
 * @author Michal Vale� <michal@vales.me>
 */
@Service
public class CategoryServiceImpl extends AbstractBaseDaoServiceImpl<Category> implements CategoryService {
    @Autowired
    public CategoryServiceImpl(CategoryDao dao) {
        super(dao, Category.class);
    }
    
    public Category merge(Category target, List<Category> with) {
        Category result = target;
        for (Category c : with){
            for (Kit k : c.getKits()){
                if (!result.getKits().contains(k)) {
                    result.addKit(k);
                }
            }
            getDao().delete(c);
        }
        getDao().delete(target);
        getDao().create(result);
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
