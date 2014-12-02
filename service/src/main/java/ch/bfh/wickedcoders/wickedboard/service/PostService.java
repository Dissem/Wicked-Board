package ch.bfh.wickedcoders.wickedboard.service;

import ch.bfh.wickedcoders.wickedboard.service.dto.PostDTO;

import java.util.Collection;

/**
 * Service interface for CRUD operations of domain objects of type {@link ch.bfh.wickedcoders.wickedboard.entities.Post}.
 *
 * @author pfafs1
 */
public interface PostService {
    public PostDTO create(long topicId, PostDTO post);
    public PostDTO read(long topicId, long id);
    public Collection<PostDTO> list(long topicId);
    public PostDTO update(long topicId, PostDTO post);
    public void delete(long topicId, PostDTO post);
}
