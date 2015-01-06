package ch.bfh.wickedcoders.wickedboard.service;

import ch.bfh.wickedcoders.wickedboard.service.dto.TopicDTO;

import java.util.Collection;

/**
 * Service interface for CRUD operations of domain objects of type {@link ch.bfh.wickedcoders.wickedboard.entities.Topic}.
 *
 * @author pfafs1
 */
public interface TopicService {
    public TopicDTO create(TopicDTO topic);
    public TopicDTO read(long id);
    public Collection<TopicDTO> list(final Integer parentId);
    public TopicDTO update(TopicDTO topic);
    public void delete(TopicDTO topic);
}
