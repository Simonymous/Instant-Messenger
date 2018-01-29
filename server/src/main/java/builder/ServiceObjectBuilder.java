package builder;

import service.classes.GroupServiceImpl;
import service.classes.MessageServiceImpl;
import service.classes.UserServiceImpl;
import service.interfaces.GroupService;
import service.interfaces.MessageService;
import service.interfaces.UserService;

public class ServiceObjectBuilder {
    public static GroupService getGroupServiceObject(){
        return new GroupServiceImpl();
    }

    public static MessageService getMessageServiceObject(){
        return new MessageServiceImpl();
    }

    public static UserService getUserServiceObject(){
        return new UserServiceImpl();
    }
}
