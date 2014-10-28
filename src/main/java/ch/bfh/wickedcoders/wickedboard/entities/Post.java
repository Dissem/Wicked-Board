package ch.bfh.wickedcoders.wickedboard.entities;

import java.time.LocalDateTime;

/**
 * Created by chris on 28.10.14.
 */
public class Post {
    private final Topic topic;
    private String title;
    private String text;
    private LocalDateTime created;
    private LocalDateTime edited;
    private User user;

    public Post(Topic topic, String title, String text, User user) {
        this.topic = topic;
        this.title = title;
        this.text = text;
        this.created = LocalDateTime.now();
        this.user = user;
    }

    public Topic getTopic() {
        return topic;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public LocalDateTime getCreated() {
        return created;
    }

    public LocalDateTime getEdited() {
        return edited;
    }

    public User getUser() {
        return user;
    }
}
