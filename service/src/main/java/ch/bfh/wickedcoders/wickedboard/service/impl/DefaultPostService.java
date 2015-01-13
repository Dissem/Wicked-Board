package ch.bfh.wickedcoders.wickedboard.service.impl;

import ch.bfh.wickedcoders.wickedboard.entities.Post;
import ch.bfh.wickedcoders.wickedboard.repository.PostRepository;
import ch.bfh.wickedcoders.wickedboard.service.PostService;
import ch.bfh.wickedcoders.wickedboard.service.TopicService;
import ch.bfh.wickedcoders.wickedboard.service.UserService;
import ch.bfh.wickedcoders.wickedboard.service.dto.PostDTO;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import javax.inject.Inject;
import javax.inject.Named;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

/**
 * Default implementation of {@link ch.bfh.wickedcoders.wickedboard.service.PostService}.
 *
 * @author deluc1
 */
@Named
public class DefaultPostService implements PostService {

    @Inject
    private TopicService topicService;

    @Inject
    private PostRepository postRepository;

    @Inject
    private UserService userService;

    private ModelMapper mapper = new ModelMapper();

    @Override
    public PostDTO create(long topicId, PostDTO postDTO) {
        postDTO.setTopic(topicService.read(topicId));
        if (postDTO.getUser() == null) {
            //read user if not set
            postDTO.setUser(userService.read(postDTO.getEmail()));
        }
        LocalDateTime dateNow = LocalDateTime.now();
        postDTO.setCreated(dateNow);
        postDTO.setEdited(dateNow);
        Post post = mapper.map(postDTO, Post.class);
        post = postRepository.save(post);
        return mapper.map(post, PostDTO.class);
    }

    @Override
    public PostDTO read(long topicId, long id) {
        Post post = postRepository.findByTopicIdAndId(topicId, id);
        return mapper.map(post, PostDTO.class);
    }

    @Override
    public Collection<PostDTO> list(long topicId) {
        Collection<Post> post = postRepository.findByTopicId(topicId);
        Type targetListType = new TypeToken<List<PostDTO>>() {}.getType();
        return mapper.map(post, targetListType);
    }

    @Override
    public PostDTO update(long topicId, PostDTO postDTO) {
        //read to set created date (never changes)
        Post postDB = postRepository.findByTopicIdAndId(topicId, postDTO.getId());
        if (postDB != null) {
            //set created from db
            postDTO.setCreated(postDB.getCreated());

            postDTO.setTopic(topicService.read(topicId));
            if (postDTO.getUser() == null) {
                //read user if not set
                postDTO.setUser(userService.read(postDTO.getEmail()));
            }
            LocalDateTime dateNow = LocalDateTime.now();
            postDTO.setEdited(dateNow);
            Post post = mapper.map(postDTO, Post.class);
            post = postRepository.save(post);
            return mapper.map(post, PostDTO.class);
        }
        else {
            return null;
        }
    }

    @Override
    public void delete(long topicId, PostDTO postDTO) {
        postDTO.setTopic(topicService.read(topicId));
        Post post = mapper.map(postDTO, Post.class);
        postRepository.delete(post);
    }

}
