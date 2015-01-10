package ch.bfh.wickedcoders.wickedboard.service.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chris on 28.10.14.
 */
public class TopicDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String title;
    private Long parentId;
    private TopicDTO parent;
    private List<LabelDTO> labels = new LinkedList<>();
    private List<GroupDTO> groups = new LinkedList<>();

    public TopicDTO() {
        // JPA
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParent(TopicDTO parent) {
        this.parent = parent;
        if (this.parent != null) {
            setParentId(this.parent.getId());
        }
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
