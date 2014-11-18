package ch.bfh.wickedcoders.wickedboard.service.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 28.10.14.
 */
public class GroupDTO {

    private String name;

    private List<UserDTO> users = new ArrayList<>();

    private GroupDTO parent;

    public GroupDTO(String name) {
        this.name = name;
    }

    public GroupDTO() {
        // JPA
    }

    public String getName() {
        return name;
    }

    public void setParent(GroupDTO parent) {
        this.parent = parent;
    }

    public GroupDTO getParent() {
        return parent;
    }

    public void addUser(UserDTO user) {
        users.add(user);
    }

    public List<UserDTO> getUsers() {
        return users;
    }
}
