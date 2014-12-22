package ch.bfh.wickedcoders.wickedboard.rest.controller;

import ch.bfh.wickedcoders.wickedboard.service.PostService;
import ch.bfh.wickedcoders.wickedboard.service.dto.PostDTO;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@Controller
@RequestMapping("/topics/{topicId}/posts")
public class PostController {

    @Inject
    private PostService postService;

    /**
     * Create
     */
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public PostDTO create(@RequestBody PostDTO post, @PathVariable long topicId) {
        PostDTO createdPost = postService.create(topicId, post);
        System.out.println("Post created with id = " + createdPost.getId());
        return createdPost;
    }

    /**
     * ReadAll
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Collection<PostDTO> list(@PathVariable long topicId) {
        System.out.println("Collection of Post requested");
        return postService.list(topicId);
    }

    /**
     * Read
     */
    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    @ResponseBody
    public PostDTO read(@PathVariable long topicId, @PathVariable long id) {
        System.out.println("Post requested with id = " + id);
        return postService.read(topicId, id);
    }

    /**
     * Update
     */
    @RequestMapping(value = "{id}", method = RequestMethod.PUT)
    @ResponseBody
    public PostDTO update(@RequestBody PostDTO post, @PathVariable long topicId, @PathVariable long id) {
        PostDTO updatedPost = postService.update(topicId, post);
        System.out.println("Post updated with id = " + updatedPost.getId());
        return updatedPost;
    }

    /**
     * Delete
     */
    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long topicId, @PathVariable long id) {
        PostDTO post = postService.read(topicId, id);
        postService.delete(topicId, post);
        System.out.println("Delete Post with id = " + id);
    }
}
