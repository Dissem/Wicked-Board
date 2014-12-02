package ch.bfh.wickedcoders.wickedboard.entities;

import ch.bfh.wickedcoders.wickedboard.utils.SecurityUtils;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by chris on 28.10.14.
 */
@Entity
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String email;
    private String name;
    private String passwordHash;


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.passwordHash = SecurityUtils.getHashedPassword(password);
    }

    public User() {
        // JPA
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkPassword(String password) {
        return SecurityUtils.checkPassword(password, passwordHash);
    }
}
