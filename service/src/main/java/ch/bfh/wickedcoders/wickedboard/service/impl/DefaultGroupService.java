package ch.bfh.wickedcoders.wickedboard.service.impl;

import ch.bfh.wickedcoders.wickedboard.entities.User;
import ch.bfh.wickedcoders.wickedboard.repository.UserRepository;
import ch.bfh.wickedcoders.wickedboard.service.UserService;
import ch.bfh.wickedcoders.wickedboard.service.dto.UserDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * default implementation of {@link ch.bfh.wickedcoders.wickedboard.service.UserService}.
 *
 * @user pfafs1
 */
public class DefaultGroupService implements UserService {
    @Inject
    private UserRepository userRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public UserDTO create(UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        User persistedUser = userRepository.save(user);
        return mapper.map(persistedUser, UserDTO.class);
    }

    @Override
    public UserDTO read(String name) {
        User user = userRepository.findOne(name);
        if (user == null) return null;
        return mapper.map(user, UserDTO.class);
    }

    @Override
    public Collection<UserDTO> list() {
        Iterable<User> users = userRepository.findAll();
        Type listType = new TypeToken<Collection<UserDTO>>() {
        }.getType();
        return mapper.map(users, listType);
    }

    @Override
    public UserDTO update(UserDTO userDTO) {
        User user = mapper.map(userDTO, User.class);
        User updatedUser = userRepository.save(user);
        return mapper.map(updatedUser, UserDTO.class);
    }

    @Override
    public void delete(UserDTO userDTO) {
        User user = userRepository.findOne(userDTO.getName());
        userRepository.delete(user);
    }
}
