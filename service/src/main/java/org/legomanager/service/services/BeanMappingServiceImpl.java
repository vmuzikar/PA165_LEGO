package org.legomanager.service.services;

import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Service for bean mapping (i.e. conversion between an entity and a DTO)
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
@Service
public class BeanMappingServiceImpl implements BeanMappingService {
    @Autowired
    private Mapper mapper;

    public <T> T map(Object obj, Class<T> mapTo) {
        return mapper.map(obj, mapTo);
    }

    public <T> Collection<T> map(Collection<?> objs, Class<T> mapTo) {
        List<T> ret = new ArrayList<T>();
        for (Object obj : objs) {
            ret.add(mapper.map(obj, mapTo));
        }
        return ret;
    }
}
