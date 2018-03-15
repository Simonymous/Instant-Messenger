package im.model.interfaces;

/**
 * interface for message
 */
public interface Message {

    long getMessageId();

    void setMessageId(long messageId);

    int getGroup();

    void setGroup(int aGroup);

    String getContent();

    void setContent(String content);

    int getUser();

    void setUser(int aUser);

}
