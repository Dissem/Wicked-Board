package ch.bfh.wickedcoders.wickedboard.repository;

import ch.bfh.wickedcoders.wickedboard.entities.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for {@link ch.bfh.wickedcoders.wickedboard.entities.User} objects.
 */
public interface UserRepository extends CrudRepository<User, String> {
}
