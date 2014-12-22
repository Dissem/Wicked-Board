package ch.bfh.wickedcoders.wickedboard.rest.controller;

import ch.bfh.wickedcoders.wickedboard.service.GroupService;
import ch.bfh.wickedcoders.wickedboard.service.dto.GroupDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@Controller
@RequestMapping("/groups")
public class GroupController {

    @Inject
    private GroupService groupService;

    /**
     * Create
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public GroupDTO create(@RequestBody GroupDTO group) {
        GroupDTO groupCreated = groupService.create(group);
        System.out.println("Group created with name = " + group.getName());
        return groupCreated;
    }

    /**
     * ReadAll
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<GroupDTO> list() {
        System.out.println("Collection of Group requested");
        return groupService.list();
    }

    /**
     * Read
     */
    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    @ResponseBody
    public GroupDTO read(@PathVariable String name) {
        System.out.println("Group requested with name = " + name);
        return groupService.read(name);
    }

    /**
     * Update
     */
    @RequestMapping(value = "{name}", method = RequestMethod.PUT)
    @ResponseBody
    public GroupDTO update(@RequestBody GroupDTO group, @PathVariable String name) {
        group.setName(name);
        GroupDTO updatedGroup = groupService.update(group);
        System.out.println("Group updated with name = " + group.getName());
        return updatedGroup;
    }

    /**
     * Delete
     */
    @RequestMapping(value = "{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String name) {
        GroupDTO group = groupService.read(name);
        groupService.delete(group);
        System.out.println("Delete Group with name = " + name);
    }
}