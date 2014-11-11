package ch.bfh.wickedcoders.wickedboard;

import ch.bfh.wickedcoders.wickedboard.entities.Group;
import ch.bfh.wickedcoders.wickedboard.entities.User;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.criteria.CriteriaBuilder;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;


/**
 * Created by Claudio on 11.11.14.
 */
public class EntityReadWriteTest {

    @Test
    public void testWriteAndThenRead() {
        // Write
        Group group = new Group("TestGroup");
        group.addUser(new User("Peter Muster", "peter@muster.ch", "pfu"));
        group.addUser(new User("Peter Mueller", "peter@mueller.ch", "pfu2"));

        EntityManager em = Persistence.createEntityManagerFactory("domain").createEntityManager();

        em.getTransaction().begin();
        em.persist(group);
        em.getTransaction().commit();

        // Read
        em = Persistence.createEntityManagerFactory("domain").createEntityManager();
        CriteriaBuilder criteria = em.getCriteriaBuilder();
        List<Group> groups = em.createQuery(criteria.createQuery(Group.class)).getResultList();
        assertEquals(1, groups.size());
        assertEquals("TestGroup", groups.get(0).getName());
    }
}
