package service.interfaces;


public interface ServerNotify {

    void notifyNewGroup();

    void notifyRemovedGroup();

    void notifyNewMessage(final String id);

}