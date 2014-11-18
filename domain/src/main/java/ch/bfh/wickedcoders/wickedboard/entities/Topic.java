package ch.bfh.wickedcoders.wickedboard.entities;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by chris on 28.10.14.
 */
@Entity
public class Topic {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String title;
    @OneToOne(cascade = ALL, fetch = EAGER)
    private Topic parent;
    @ManyToMany(cascade = ALL, fetch = EAGER)
    private List<Label> labels = new LinkedList<>();
    @ManyToMany(cascade = ALL, fetch = EAGER)
    private List<Group> groups = new LinkedList<>();

    public Topic(String title) {
        this.title = title;
    }

    public Topic() {
        // JPA
    }

    public String getTitle() {
        return title;
    }

    public void setParent(Topic parent) {
        this.parent = parent;
    }

    public Topic getParent() {
        return parent;
    }

    public void addLabel(Label label) {
        labels.add(label);
    }

    public List<Label> getLabels() {
        return labels;
    }

    public void addGroup(Group group) {
        groups.add(group);
    }

    public List<Group> getGroups() {
        return groups;
    }
}
