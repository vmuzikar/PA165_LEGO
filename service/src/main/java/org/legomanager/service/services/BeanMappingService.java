package org.legomanager.service.services;

import java.util.Collection;

/**
 * Interface for service for bean mapping (i.e. conversion between an entity and a DTO)
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface BeanMappingService {
    <T> T map(Object obj, Class<T> mapTo);
    <T> Collection<T> map(Collection<?> objs, Class<T> mapTo);
}
