package builder;

import model.classes.*;
import model.interfaces.*;

import java.util.Date;

/**
 * Generets implementations of the Model Classes
 */
public class ModelObjectBuilder {
    public static User getUserObject(){
        return new UserImpl();
    }

    public static User getUserObject(int userId){
        return new UserImpl(userId);
    }

    public static User getUserObject(String username, String password){
        return new UserImpl(username, password);
    }

    public static Group getGroupObject(){
        return new GroupImpl();
    }

    public static Group getGroupObject(String groupname){
        return new GroupImpl(groupname);
    }

    public static Group getGroupObject(int id){
        return new GroupImpl(id);
    }

    public static Group getGroupObject(int id, String name){
        return new GroupImpl(id, name);
    }

    public static Group_User getGroup_UserObject(){
        return new Group_UserImpl();
    }

    public static Group_User getGroup_UserObject(int userId, int groupId){
        return new Group_UserImpl(userId, groupId);
    }

    public static Message getMessageObject(){
        return new MessageImpl();
    }

    public static Message getMessageObject(long messageId){
        return new MessageImpl(messageId);
    }

    public static Message getMessageObject(int aGroup, int aUser, String content){
        return new MessageImpl(aGroup, aUser, content);
    }
}
