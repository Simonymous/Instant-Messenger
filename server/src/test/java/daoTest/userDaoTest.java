package daoTest;

import model.classes.UserImpl;
import org.hibernate.Session;
import org.junit.*;
import utils.HibernateUtil;

import static junit.framework.TestCase.assertEquals;

public class userDaoTest {
    @Test
    public void testPersistent(){
        UserImpl user = new UserImpl();
        user.setActive('a');
        user.setUsername("TestUser");
        user.setPassword("");

        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.saveOrUpdate(user);
        session.getTransaction().commit();

        UserImpl userR = session.get(UserImpl.class,1 );

        assertEquals("TestUser", userR.getUsername());
    }
}
