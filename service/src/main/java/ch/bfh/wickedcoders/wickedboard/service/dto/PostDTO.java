package ch.bfh.wickedcoders.wickedboard.service.dto;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
    private Long createdDate;//only for json
    private Long editedDate;//only for json
    private String email;
    private UserDTO user;

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
    }

    public String getTitle() {
        return title;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }


    public void setCreated(LocalDateTime created) {
        this.created = created;
        if (created == null) {
            this.createdDate = null;
        }
        else {
            Instant instant = created.atZone(ZoneId.systemDefault()).toInstant();
            this.createdDate = Date.from(instant).getTime();
        }
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public void setEdited(LocalDateTime edited) {
        this.edited = edited;
        if (edited == null) {
            this.editedDate = null;
        }
        else {
            Instant instant = edited.atZone(ZoneId.systemDefault()).toInstant();
            this.editedDate = Date.from(instant).getTime();
        }
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public Long getCreatedDate() {
        return this.createdDate;
    }
    public Long getEditedDate() {
        return this.editedDate;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setUser(UserDTO user) {
        this.user = user;
        if (this.user != null) {
            setEmail(this.user.getEmail());
        }
    }

    public UserDTO getUser() {
        return user;
    }
}
