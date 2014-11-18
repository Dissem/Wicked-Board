package ch.bfh.wickedcoders.wickedboard.repository;

import ch.bfh.wickedcoders.wickedboard.entities.Group;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for {@link Group} objects.
 */
public interface GroupRepository extends CrudRepository<Group, String> {
}
