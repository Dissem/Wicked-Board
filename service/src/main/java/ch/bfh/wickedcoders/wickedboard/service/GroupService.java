package ch.bfh.wickedcoders.wickedboard.service;

import ch.bfh.wickedcoders.wickedboard.service.dto.GroupDTO;

import java.util.Collection;

/**
 * Service interface for CRUD operations of domain objects of type {@link ch.bfh.wickedcoders.wickedboard.entities.Group}.
 *
 * @author pfafs1
 */
public interface GroupService {
    public GroupDTO create(GroupDTO group);
    public GroupDTO read(String name);
    public Collection<GroupDTO> list();
    public GroupDTO update(GroupDTO group);
    public void delete(GroupDTO group);
}
