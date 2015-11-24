package org.legomanager.service.services;

import org.legomanager.api.representantions.ModelRepresentation;
import org.legomanager.persistence.entities.Kit;

/**
 * Interface for service for converting 3D models to kits
 *
 * @author Vaclav Muzikar <vaclav@muzikari.cz>
 */
public interface ModelKitConverterService {
    Kit convertModelToKit(ModelRepresentation model);
}
