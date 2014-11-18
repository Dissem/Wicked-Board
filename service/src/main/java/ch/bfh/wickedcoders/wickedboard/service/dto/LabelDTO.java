package ch.bfh.wickedcoders.wickedboard.service.dto;

/**
 * Created by chris on 28.10.14.
 */
public class LabelDTO {

    private String name;

    public LabelDTO(String name) {
        this.name = name;
    }

    public LabelDTO() {
        // JPA
    }

    @Override
    public String toString() {
        return name;
    }
}
