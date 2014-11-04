package ch.bfh.wickedcoders.wickedboard.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by chris on 28.10.14.
 */
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long id;

    @ManyToOne
    private final Topic topic;
    private String title;
    private String text;
    private final LocalDateTime created;
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

    public User getUser() {
        return user;
    }
}
