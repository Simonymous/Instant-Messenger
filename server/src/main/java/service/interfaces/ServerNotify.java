package service.interfaces;


public interface ServerNotify {

    void notifyUpdatedGroup();

    void notifyRemovedGroup(final String id);

    void notifyNewMessage(final String id);

}