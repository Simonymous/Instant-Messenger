package dao.classes;

import dao.interfaces.GroupDao;
import model.interfaces.Group;
import org.hibernate.Session;
import utils.HibernateUtil;

public class GroupDaoHibernateImpl implements GroupDao {
    @Override
    public void save(Group group) {
        Session session = null;

        try{
            session = HibernateUtil.getSession();
            session.beginTransaction();
            session.saveOrUpdate(group);
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
