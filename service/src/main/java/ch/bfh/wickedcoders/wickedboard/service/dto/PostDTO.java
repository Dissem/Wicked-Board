package ch.bfh.wickedcoders.wickedboard.service.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by chris on 28.10.14.
 */
public class PostDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private TopicDTO topic;
    private String title;
    private String text;
    private LocalDateTime created;
    private LocalDateTime edited;
    private UserDTO user;

    public PostDTO(TopicDTO topic, String title, String text, UserDTO user) {
        this.topic = topic;
        this.title = title;
        this.text = text;
        this.created = LocalDateTime.now();
        this.user = user;
    }

    public PostDTO() {
        // JPA
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setTopic(TopicDTO topic) {
        this.topic = topic;
    }

    public TopicDTO getTopic() {
        return topic;
    }

    public void setTitle(String title) {
        this.title = title;
        this.edited = LocalDateTime.now();
    }

    public String getTitle() {
        return title;
    }

    public void setText(String text) {
        this.text = text;
        this.edited = LocalDateTime.now();
    }

    public String getText() {
        return text;
    }

    public void setCreated(LocalDateTime created) {
        this.created = created;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }

    public UserDTO getUser() {
        return user;
    }
}
