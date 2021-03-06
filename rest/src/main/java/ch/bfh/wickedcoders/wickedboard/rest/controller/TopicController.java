package ch.bfh.wickedcoders.wickedboard.rest.controller;

import ch.bfh.wickedcoders.wickedboard.service.TopicService;
import ch.bfh.wickedcoders.wickedboard.service.dto.TopicDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@Controller
@RequestMapping("/topics")
public class TopicController {

    @Inject
    private TopicService topicService;

    /**
     * Create
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public TopicDTO create(@RequestBody TopicDTO topic) {

        // Read parent topic if topic id is present.
        if (topic.getParentId() != null && topic.getParent() == null) {
            final TopicDTO parentTopic = topicService.read(topic.getParentId());
            topic.setParent(parentTopic);
        }

        // Save topic
        TopicDTO createTopic = topicService.create(topic);
        System.out.println("Topic created with id = " + createTopic.getId());
        return createTopic;
    }

    /**
     * ReadAll
     */
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET)
    public Collection<TopicDTO> list() {
        System.out.println("Collection of Topic requested");
        return topicService.list(null);
    }

    /**
     * ReadChildren.
     */
    @ResponseBody
    @RequestMapping(value = "/{topicId}/topics", method = RequestMethod.GET)
    public Collection<TopicDTO> list(@PathVariable final Integer topicId) {
        System.out.println("Collection of Topic requested");
        return topicService.list(topicId);
    }

    /**
     * Read
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public TopicDTO read(@PathVariable long id) {
        System.out.println("Topic requested with id = " + id);
        return topicService.read(id);
    }

    /**
     * Update
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseBody
    public TopicDTO update(@RequestBody TopicDTO topic, @PathVariable long id) {

        // Set topic id according to path variable
        topic.setId(id);

        // Read parent topic if topic id is present.
        if (topic.getParentId() != null && topic.getParent() == null) {
            final TopicDTO parentTopic = topicService.read(topic.getParentId());
            topic.setParent(parentTopic);
        }

        // Update topic
        TopicDTO updatedTopic = topicService.update(topic);
        System.out.println("Topic updated with id = " + updatedTopic.getId());
        return updatedTopic;
    }

    /**
     * Delete
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        TopicDTO topic = topicService.read(id);
        topicService.delete(topic);
        System.out.println("Delete Topic with id = " + id);
    }
}