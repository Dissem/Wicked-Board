package ch.bfh.wickedcoders.wickedboard.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by chris on 28.10.14.
 */
public class Group {
    private String name;
    private List<User> users = new ArrayList<>();
    private Group parent;

    public Group(String name){
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setParent(Group parent) {
        this.parent = parent;
    }

    public Group getParent() {
        return parent;
    }

    public void addUser(User user){
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
