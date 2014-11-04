package ch.bfh.wickedcoders.wickedboard.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by chris on 28.10.14.
 */
@Entity
public class Label {
    @Id
    private final String name;

    public Label(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
