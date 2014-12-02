package ch.bfh.wickedcoders.wickedboard.service.dto;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by chris on 28.10.14.
 */
public class TopicDTO {

    private Long id;
    private String title;
    private TopicDTO parent;
    private List<LabelDTO> labels = new LinkedList<>();
    private List<GroupDTO> groups = new LinkedList<>();

    public TopicDTO(String title) {
        this.title = title;
    }

    public TopicDTO() {
        // JPA
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public void setParent(TopicDTO parent) {
        this.parent = parent;
    }

    public TopicDTO getParent() {
        return parent;
    }

    public void addLabel(LabelDTO label) {
        labels.add(label);
    }

    public List<LabelDTO> getLabels() {
        return labels;
    }

    public void addGroup(GroupDTO group) {
        groups.add(group);
    }

    public List<GroupDTO> getGroups() {
        return groups;
    }
}
