package builder;

import dao.classes.*;
import dao.interfaces.*;

/**
 * Generets implementations of the Dao Classes
 */
public class DaoObjectBuilder {
    public static UserDao getUserDaoObject() {
        return new UserDaoImpl();
    }

    //TODO Sobald die Implementierungen fertig sind einkommentieren
//    public static GroupDao getGroupDaoObject() {
//        return new GroupDaoImpl();
//    }
//
//    public static Group_UserDao getGroup_UserDaoObject() {
//        return new Group_UserDaoImpl();
//    }
//
//    public static MessageDao getMessageDaoObject() {
//        return new MessageDaoImpl();
//    }
}
