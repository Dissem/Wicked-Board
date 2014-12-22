package ch.bfh.wickedcoders.wickedboard.rest.controller;

import ch.bfh.wickedcoders.wickedboard.service.LabelService;
import ch.bfh.wickedcoders.wickedboard.service.dto.LabelDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@Controller
@RequestMapping("/labels")
public class LabelController {

    @Inject
    private LabelService labelService;

    /**
     * Create
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public LabelDTO create(@RequestBody LabelDTO label) {
        LabelDTO createdLabel = labelService.create(label);
        System.out.println("Label created with name = " + label.getName());
        return createdLabel;
    }

    /**
     * ReadAll
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<LabelDTO> list() {
        System.out.println("Collection of Label requested");
        return labelService.list();
    }

    /**
     * Read
     */
    @RequestMapping(value = "{name}", method = RequestMethod.GET)
    @ResponseBody
    public LabelDTO read(@PathVariable String name) {
        System.out.println("Label requested with name = " + name);
        return labelService.read(name);
    }

    /**
     * Update
     */
    @RequestMapping(value = "{name}", method = RequestMethod.PUT)
    @ResponseBody
    public LabelDTO update(@RequestBody LabelDTO label, @PathVariable String name) {
        label.setName(name);
        LabelDTO updatedGroup = labelService.update(label);
        System.out.println("Label updated with name = " + name);
        return updatedGroup;
    }

    /**
     * Delete
     */
    @RequestMapping(value = "{name}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable String name) {
        final LabelDTO label = labelService.read(name);
        labelService.delete(label);
        System.out.println("Delete Label with name = " + name);
    }
}