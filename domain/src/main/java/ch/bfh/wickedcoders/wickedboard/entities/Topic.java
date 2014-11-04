package ch.bfh.wickedcoders.wickedboard.entities;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by chris on 28.10.14.
 */
public class Topic {
    private long id;
    private String title;
    private Topic parent;
    private List<Label> labels = new LinkedList<>();
    private List<Group> groups = new LinkedList<>();

    public Topic(String title) {
        this.title = title;
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
