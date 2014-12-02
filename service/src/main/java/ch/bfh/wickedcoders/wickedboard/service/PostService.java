package ch.bfh.wickedcoders.wickedboard.service;

import ch.bfh.wickedcoders.wickedboard.service.dto.PostDTO;

import java.util.Collection;

/**
 * Service interface for CRUD operations of domain objects of type {@link ch.bfh.wickedcoders.wickedboard.entities.Post}.
 *
 * @author pfafs1
 */
public interface PostService {
    public PostDTO create(PostDTO post);
    public PostDTO read(long id);
    public Collection<PostDTO> list();
    public PostDTO update(PostDTO post);
    public void delete(PostDTO post);
}
