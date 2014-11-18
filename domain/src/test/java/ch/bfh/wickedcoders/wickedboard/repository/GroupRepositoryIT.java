package ch.bfh.wickedcoders.wickedboard.repository;

import ch.bfh.wickedcoders.wickedboard.entities.Group;
import ch.bfh.wickedcoders.wickedboard.entities.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Created by Claudio on 11.11.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/persistenceContext.xml")
public class GroupRepositoryIT {

    @Inject
    private GroupRepository groupRepository;

    @Test
    public void testUserWriteRead() {

        // Create new instance
        Group group = new Group("TestGroup");
        group.addUser(new User("Peter Muster", "peter@muster.ch", "pfu"));
        group.addUser(new User("Peter Mueller", "peter@mueller.ch", "pfu2"));

        // Save Group
        groupRepository.save(group);

        // Read Group
        Group readGroup = groupRepository.findOne(group.getName());
        Assert.assertEquals("Group has 2 users", 2, readGroup.getUsers().size());

        // Update Group
        Group parentGroup = new Group("TestParentGroup");
        readGroup.setParent(parentGroup);
        groupRepository.save(readGroup);
        Group readGroup2 = groupRepository.findOne(group.getName());
        Assert.assertEquals("Parent group matches", parentGroup.getName(), readGroup2.getParent().getName());

        // Delete
        groupRepository.delete(readGroup);
        readGroup = groupRepository.findOne(readGroup.getName());
        Assert.assertNull(readGroup);
    }
}
