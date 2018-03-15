package builder;

import dao.classes.*;
import dao.interfaces.*;

/**
 * Generates implementations of the Dao Classes
 */
public class DaoObjectBuilder {
    public static UserDao getUserDaoObject() {
        return new UserDaoImpl();
    }

    /**
     * method to create a GroupDaoImpl object
     *
     * @return GroupDao
     */
    public static GroupDao getGroupDaoObject() {
        return new GroupDaoImpl();
    }
    
    /**
     * method to create a Group_UserDao object
     *
     * @return Group_UserDao
     */
    public static Group_UserDao getGroup_UserDaoObject() {
        return new Group_UserDaoImpl();
    }

    /**
     * method to create a MessageDao object
     *
     * @return MessageDao
     */
    public static MessageDao getMessageDaoObject() {
        return new MessageDaoImpl();
    }
    
}
