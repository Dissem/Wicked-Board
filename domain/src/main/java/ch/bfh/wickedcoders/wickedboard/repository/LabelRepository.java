package ch.bfh.wickedcoders.wickedboard.repository;

import ch.bfh.wickedcoders.wickedboard.entities.Label;
import org.springframework.data.repository.CrudRepository;

/**
 * Repository for {@link ch.bfh.wickedcoders.wickedboard.entities.Label} objects.
 */
public interface LabelRepository extends CrudRepository<Label, String> {
}
