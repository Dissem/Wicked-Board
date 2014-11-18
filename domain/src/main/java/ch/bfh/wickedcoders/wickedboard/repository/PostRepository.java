package ch.bfh.wickedcoders.wickedboard.repository;

import ch.bfh.wickedcoders.wickedboard.entities.Post;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for {@link ch.bfh.wickedcoders.wickedboard.entities.Post} objects.
 */
public interface PostRepository extends CrudRepository<Post, Long> {
}
