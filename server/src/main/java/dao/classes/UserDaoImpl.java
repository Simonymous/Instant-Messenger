package dao.classes;

import dao.interfaces.UserDao;
import model.classes.UserImpl;
import model.interfaces.User;
import org.hibernate.Session;
import org.hibernate.query.Query;
import utils.HibernateUtil;

import java.util.ArrayList;

public class UserDaoImpl implements UserDao{
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
            User user = (User) session.get(UserImpl.class, Id);
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

    public User getUser(String name){
        Session session = null;
        User user = null;
        String queryString = "SELECT object FROM model.classes.UserImpl WHERE name = " + name;

        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            Query q = session.createQuery(queryString);
            user = (User) q.uniqueResult();
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

    public ArrayList<User> getList(){
        Session session = null;
        ArrayList<User> resultList;
        String queryString = "SELECT a FROM model.classes.UserImpl a";

        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            Query q = session.createQuery(queryString);
            resultList = (ArrayList<User>) q.list();
            session.getTransaction().commit();
            return resultList;
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
