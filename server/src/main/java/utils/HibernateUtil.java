package utils;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static final SessionFactory sessionFactory;

    static {
        try{
            sessionFactory = new Configuration().configure().buildSessionFactory();
        }
        catch(Throwable ex){
            System.err.println("Session Factory konnte nicht initialisiert werden" + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession(){
        return getSessionFactory().getCurrentSession();
    }

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    public static void closeSession(Session session){
        try{
            if ((session != null) && (session.isOpen())){
                session.close();
            }
        }
        catch(HibernateException he){

        }
    }
}
