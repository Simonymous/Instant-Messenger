package dao.classes;

import dao.interfaces.MessageDao;
import model.interfaces.Message;
import org.hibernate.Session;
import utils.HibernateUtil;

//TODO Implements hinzufügen
public class MessageDaoImpl {
    public void save(Message message) {
        Session session = null;

        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(message);
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

    public void delete(Message message){
        Session session = null;

        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            if(message != null){
                session.delete(message);
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

    public Message getMessage(int Id){
        Session session = null;

        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            Message message = (Message) session.get(UserDaoImpl.class, Id);
            session.getTransaction().commit();
            return message;
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
