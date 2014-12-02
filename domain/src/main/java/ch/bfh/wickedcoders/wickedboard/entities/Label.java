package ch.bfh.wickedcoders.wickedboard.entities;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by chris on 28.10.14.
 */
@Entity
public class Label implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String name;

    public Label(String name) {
        this.name = name;
    }

    public Label() {
        // JPA
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
