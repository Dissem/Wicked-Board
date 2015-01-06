package ch.bfh.wickedcoders.wickedboard.rest.controller;

import ch.bfh.wickedcoders.wickedboard.service.UserService;
import ch.bfh.wickedcoders.wickedboard.service.dto.LabelDTO;
import ch.bfh.wickedcoders.wickedboard.service.dto.UserDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@Controller
@RequestMapping("/users")
public class UserController {

    @Inject
    private UserService userService;

    /**
     * Create
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public UserDTO create(@RequestBody UserDTO user) {
        user.calculateHash();
        System.out.println(user);
        UserDTO createdUser = userService.create(user);
        System.out.println("User created with email = " + user.getEmail());
        return createdUser;
    }

    /**
     * ReadAll
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<UserDTO> list() {
        System.out.println("Collection of User requested");
        Collection<UserDTO> users = userService.list();
        users.forEach(u -> {
            u.setPassword(null);
            u.setPasswordHash(null);
        });
        return users;
    }

    /**
     * Read
     */
    @RequestMapping(value = "/{email}", method = RequestMethod.POST)
    @ResponseBody
    public UserDTO read(@PathVariable String email, @RequestBody String password) throws Exception {
        System.out.println("User requested with email = " + email + " and password = " + password);
        UserDTO userDTO = userService.read(email);
        if (userDTO != null && userDTO.checkPassword(password)) {
            userDTO.setPasswordHash(null); // This isn't needed anymore
            System.out.println(userDTO);
            return userDTO;
        }
        throw new Exception("invalid credentials");
    }

    /**
     * Update
     */
    @RequestMapping(value = "{email}", method = RequestMethod.PUT)
    @ResponseBody
    public UserDTO update(@RequestBody UserDTO user, @PathVariable String email) {
        user.setEmail(email);
        UserDTO updatedGroup = userService.update(user);
        System.out.println("User updated with email = " + email);
        return updatedGroup;
    }

    /**
     * Delete
     */
    @RequestMapping(value = "{email}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String email) {
        final UserDTO user = userService.read(email);
        userService.delete(user);
        System.out.println("Delete User with name = " + email);
    }
}