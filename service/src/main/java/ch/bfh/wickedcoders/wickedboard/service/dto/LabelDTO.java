package ch.bfh.wickedcoders.wickedboard.service.dto;

import java.io.Serializable;

/**
 * Created by chris on 28.10.14.
 */
public class LabelDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    public LabelDTO(String name) {
        this.name = name;
    }

    public LabelDTO() {
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
