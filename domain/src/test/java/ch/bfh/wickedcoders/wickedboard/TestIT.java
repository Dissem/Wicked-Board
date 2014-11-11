package ch.bfh.wickedcoders.wickedboard;

import ch.bfh.wickedcoders.wickedboard.entities.Group;
import ch.bfh.wickedcoders.wickedboard.entities.User;
import org.junit.Assert;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;


/**
 * Created by Claudio on 11.11.14.
 */
public class TestIT {

    @Test
    public void testIt() {

        Group group = new Group("TestGroup");
        group.addUser(new User("Peter Muster", "peter@muster.ch", "pfu"));
        group.addUser(new User("Peter Mueller", "peter@mueller.ch", "pfu2"));

        EntityManager em = Persistence.createEntityManagerFactory(
                "domain").createEntityManager();

        em.getTransaction().begin();
        em.persist(group);
        em.getTransaction().commit();
    }
}
