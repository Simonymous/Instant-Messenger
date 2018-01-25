package dao.classes;

import dao.interfaces.UserDao;
import model.interfaces.User;
import org.hibernate.Session;
import utils.HibernateUtil;

public class UserDaoHibernateImpl implements UserDao{
    @Override
    public void save(User user) {
        Session session = null;

        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(user);
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
