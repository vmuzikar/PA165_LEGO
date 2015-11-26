package org.legomanager.service.services;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import org.legomanager.persistence.dao.CategoryDao;
import org.legomanager.persistence.entities.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementation of service for manipulation with kits
 *
 * @author Michal Valeš <michal@vales.me>
 */
@Service
public class CategoryServiceImpl extends AbstractBaseDaoServiceImpl<Category> implements CategoryService {
    @Autowired
    public CategoryServiceImpl(CategoryDao dao) {
        super(dao, Category.class);
    }

    public List<Category> getUnused() {
        CategoryDao categoryDao = (CategoryDao) getDao();
        List<Category> fetchedCategories = null;
        try {
            fetchedCategories = categoryDao.findAll();
        }
        catch (Exception e) {
            throw new DataRetrievalFailureException("There was an error on DAO layer");
        }
        Iterator<Category> it = fetchedCategories.iterator();
        while (it.hasNext()) {
            Category c = it.next();
            if (c.getKits().isEmpty()) {
                it.remove();
            }
        }
        return fetchedCategories;
    }

    public List<Category> getWithMostKits(int count) {
        if (count < 1) {
            throw new IllegalArgumentException("Count must be at least 1");
        }
        CategoryDao categoryDao = (CategoryDao) getDao();
        List<Category> fetchedCategories = null;
        try {
            fetchedCategories = categoryDao.findAll();
        }
        catch (Exception e) {
            throw new DataRetrievalFailureException("There was an error on DAO layer");
        }
        Collections.sort(fetchedCategories, new CategoryComparatorByKitsSize());
        List<Category> result = fetchedCategories.subList(0, Math.min(fetchedCategories.size(), count));
        return result;
    }
    
    private class CategoryComparatorByKitsSize implements Comparator<Category> {
        @Override
        public int compare(Category c1, Category c2) {
            return (-1) * (c2.getKits().size() - c1.getKits().size());
        }
    }
}
