package ch.bfh.wickedcoders.wickedboard.repository;

import ch.bfh.wickedcoders.wickedboard.entities.Topic;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Repository for {@link ch.bfh.wickedcoders.wickedboard.entities.Topic} objects.
 */

public interface TopicRepository extends CrudRepository<Topic, Long> {

    Collection<Topic> findByParentId(final Integer parentId);

}
