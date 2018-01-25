package dao.classes;

import dao.interfaces.Group_UserDao;
import model.interfaces.Group_User;
import org.hibernate.Session;
import utils.HibernateUtil;

public class Group_UserDaoHibernateImpl implements Group_UserDao {
    @Override
    public void save(Group_User group_user) {
        Session session = null;

        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(group_user);
            session.flush();
            session.getTransaction().commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            HibernateUtil.closeSession(session);
        }
    }
}
