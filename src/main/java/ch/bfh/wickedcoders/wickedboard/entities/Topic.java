package ch.bfh.wickedcoders.wickedboard.entities;

import java.util.List;

/**
 * Created by chris on 28.10.14.
 */
public class Topic {
    private long id;
    private Topic parent;
    private String title;
    private List<Label> labels;
    private List<Group> groups;
}
