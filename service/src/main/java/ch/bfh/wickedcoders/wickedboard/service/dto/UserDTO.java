package ch.bfh.wickedcoders.wickedboard.service.dto;

import ch.bfh.wickedcoders.wickedboard.utils.SecurityUtils;

import java.io.Serializable;

/**
 * Created by chris on 28.10.14.
 */
public class UserDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private String name;

    private String password;

    private String passwordHash;

    public UserDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.passwordHash = SecurityUtils.getHashedPassword(password);
    }

    public UserDTO() {
        // JPA
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void calculateHash() {
        this.passwordHash = SecurityUtils.getHashedPassword(password);
    }

    public boolean checkPassword(String password) {
        return SecurityUtils.checkPassword(password, passwordHash);
    }

    @Override
    public String toString() {
        return name + " / " + email + " / " + password + " / " + passwordHash;
    }
}
