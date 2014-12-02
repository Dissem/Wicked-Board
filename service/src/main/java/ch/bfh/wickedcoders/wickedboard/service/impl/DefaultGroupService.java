package ch.bfh.wickedcoders.wickedboard.service.impl;

import ch.bfh.wickedcoders.wickedboard.entities.Group;
import ch.bfh.wickedcoders.wickedboard.repository.GroupRepository;
import ch.bfh.wickedcoders.wickedboard.service.GroupService;
import ch.bfh.wickedcoders.wickedboard.service.dto.GroupDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import java.lang.reflect.Type;
import java.util.Collection;

/**
 * default implementation of {@link ch.bfh.wickedcoders.wickedboard.service.GroupService}.
 *
 * @group pfafs1
 */
public class DefaultGroupService implements GroupService {
    @Inject
    private GroupRepository groupRepository;
    private final ModelMapper mapper = new ModelMapper();

    @Override
    public GroupDTO create(GroupDTO groupDTO) {
        Group group = mapper.map(groupDTO, Group.class);
        Group persistedGroup = groupRepository.save(group);
        return mapper.map(persistedGroup, GroupDTO.class);
    }

    @Override
    public GroupDTO read(String name) {
        Group group = groupRepository.findOne(name);
        if (group == null) return null;
        return mapper.map(group, GroupDTO.class);
    }

    @Override
    public Collection<GroupDTO> list() {
        Iterable<Group> groups = groupRepository.findAll();
        Type listType = new TypeToken<Collection<GroupDTO>>() {
        }.getType();
        return mapper.map(groups, listType);
    }

    @Override
    public GroupDTO update(GroupDTO groupDTO) {
        Group group = mapper.map(groupDTO, Group.class);
        Group updatedGroup = groupRepository.save(group);
        return mapper.map(updatedGroup, GroupDTO.class);
    }

    @Override
    public void delete(GroupDTO groupDTO) {
        Group group = groupRepository.findOne(groupDTO.getName());
        groupRepository.delete(group);
    }
}
