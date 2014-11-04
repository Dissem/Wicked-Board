package ch.bfh.wickedcoders.wickedboard.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

/**
 * Created by chris on 28.10.14.
 */
@Entity
public class Group {
    @Id
    private String name;
    @ManyToMany(cascade = ALL, fetch = EAGER)
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
