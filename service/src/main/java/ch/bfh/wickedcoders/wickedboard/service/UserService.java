package ch.bfh.wickedcoders.wickedboard.service;

import ch.bfh.wickedcoders.wickedboard.service.dto.UserDTO;

import java.util.Collection;

/**
 * Service interface for CRUD operations of domain objects of type {@link ch.bfh.wickedcoders.wickedboard.entities.User}.
 *
 * @author pfafs1
 */
public interface UserService {
    public UserDTO create(UserDTO userDTO);
    public UserDTO read(String name);
    public Collection<UserDTO> list();
    public UserDTO update(UserDTO userDTO);
    public void delete(UserDTO userDTO);
}
