package dao.classes;

import dao.interfaces.MessageDao;
import model.interfaces.Message;
import org.hibernate.Session;
import utils.HibernateUtil;

public class MessageDaoHibernateImpl implements MessageDao{
    @Override
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
}
