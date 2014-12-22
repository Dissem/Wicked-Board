package ch.bfh.wickedcoders.wickedboard.repository;

import ch.bfh.wickedcoders.wickedboard.entities.Post;
import ch.bfh.wickedcoders.wickedboard.entities.Topic;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

/**
 * Repository for {@link ch.bfh.wickedcoders.wickedboard.entities.Post} objects.
 */
public interface PostRepository extends CrudRepository<Post, Long> {

    Collection<Post> findByTopicId(final Long topicId);

    Post findByTopicIdAndId(final Long topicId, final Long id);

}
