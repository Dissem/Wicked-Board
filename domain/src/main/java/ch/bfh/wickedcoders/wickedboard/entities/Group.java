package ch.bfh.wickedcoders.wickedboard.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;

/**
 * Created by chris on 28.10.14.
 */
@Entity(name = "UserGroup")
public class Group implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String name;
    @ManyToMany(cascade = ALL, fetch = EAGER)
    private List<User> users = new ArrayList<>();
    @OneToOne(cascade = ALL, fetch = EAGER)
    private Group parent;

    public Group(String name) {
        this.name = name;
    }

    public Group() {
        // JPA
    }

    public void setName(String name) {
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

    public void addUser(User user) {
        users.add(user);
    }

    public List<User> getUsers() {
        return users;
    }
}
