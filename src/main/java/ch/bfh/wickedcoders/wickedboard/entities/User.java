package ch.bfh.wickedcoders.wickedboard.entities;

import ch.bfh.wickedcoders.wickedboard.utils.SecurityUtils;

/**
 * Created by chris on 28.10.14.
 */
public class User {
    private String name;
    private String email;
    private String passwordHash;


    public User(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.passwordHash = SecurityUtils.getHashedPassword(password);
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public boolean checkPassword(String password) {
        return SecurityUtils.checkPassword(password, passwordHash);
    }
}
