package ch.bfh.wickedcoders.wickedboard.service.dto;

import ch.bfh.wickedcoders.wickedboard.utils.SecurityUtils;

/**
 * Created by chris on 28.10.14.
 */
public class UserDTO {

    private String email;

    private String name;

    private String passwordHash;

    public UserDTO(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.passwordHash = SecurityUtils.getHashedPassword(password);
    }

    public UserDTO() {
        // JPA
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
