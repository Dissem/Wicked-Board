package ch.bfh.wickedcoders.wickedboard.entities;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.CascadeType.ALL;
import static javax.persistence.FetchType.EAGER;
import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by chris on 28.10.14.
 */
@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @ManyToOne(cascade = ALL, fetch = EAGER)
    private Topic topic;
    private String title;
    private String text;
    private LocalDateTime created;
    private LocalDateTime edited;
    @OneToOne(cascade = ALL, fetch = EAGER)
    private User user;

    public Post(Topic topic, String title, String text, User user) {
        this.topic = topic;
        this.title = title;
        this.text = text;
        this.created = LocalDateTime.now();
        this.user = user;
    }

    public Post() {
        // JPA
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }

    public Topic getTopic() {
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

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }
}
