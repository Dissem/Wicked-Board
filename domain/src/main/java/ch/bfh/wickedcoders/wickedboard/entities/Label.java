package ch.bfh.wickedcoders.wickedboard.entities;

/**
 * Created by chris on 28.10.14.
 */
public class Label {
    private final String name;

    public Label(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
