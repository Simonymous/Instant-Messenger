package model.interfaces;

public interface Message {
    long getMessageId();
    void setMessageId(long messageId);
    Group getGroup();
    void setGroup(Group aGroup);
    String getContent();
    void setContent(String content);
    User getUser();
    void setUser(User aUser);
}
