package ch.bfh.wickedcoders.wickedboard.service;

import ch.bfh.wickedcoders.wickedboard.service.dto.LabelDTO;

import java.util.Collection;

/**
 * Service interface for CRUD operations of domain objects of type {@link ch.bfh.wickedcoders.wickedboard.entities.Label}.
 *
 * @author pfafs1
 */
public interface LabelService {
    public LabelDTO create(LabelDTO label);
    public LabelDTO read(String name);
    public Collection<LabelDTO> list();
    public LabelDTO update(LabelDTO label);
    public void delete(LabelDTO label);
}
