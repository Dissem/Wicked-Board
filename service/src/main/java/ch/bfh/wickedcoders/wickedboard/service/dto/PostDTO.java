package ch.bfh.wickedcoders.wickedboard.service.dto;

import java.time.LocalDateTime;

/**
 * Created by chris on 28.10.14.
 */
public class PostDTO {

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

    public Long getId() {
        return id;
    }

    public TopicDTO getTopic() {
        return topic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        this.edited = LocalDateTime.now();
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        this.edited = LocalDateTime.now();
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public UserDTO getUser() {
        return user;
    }
}
