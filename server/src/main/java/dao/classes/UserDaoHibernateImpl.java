package dao.classes;

import dao.interfaces.UserDao;
import model.interfaces.User;
import org.hibernate.Session;
import utils.HibernateUtil;

public class UserDaoHibernateImpl implements UserDao{
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

    public void delete(User user){
        Session session = null;

        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            if(user != null){
                session.delete(user);
                session.flush();
            }
            session.getTransaction().commit();
        }
        catch(Exception e){
            e.printStackTrace();
        }
        finally {
            HibernateUtil.closeSession(session);
        }
    }

    public User getUser(int Id){
        Session session = null;

        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            User user = (User) session.get(UserDaoHibernateImpl.class, Id);
            session.getTransaction().commit();
            return user;
        }
        catch(Exception e){
            e.printStackTrace();
            return null;
        }
        finally {
            HibernateUtil.closeSession(session);
        }
    }
}
