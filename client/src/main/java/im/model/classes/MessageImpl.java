package im.model.classes;

import im.model.interfaces.Group;
import im.model.interfaces.Message;
import im.model.interfaces.User;

//TODO Eventuell Content als eigene Klasse
public class MessageImpl implements Message {
    private long messageId;
    private Group group;
    private User user;
    private String content;

    public MessageImpl() {
    }

    public MessageImpl(long messageId){
        this.setMessageId(messageId);
    }

    public MessageImpl(Group aGroup, User aUser, String content){
        this.setGroup(aGroup);
        this.setUser(aUser);
        this.setContent(content);
    }

    public long getMessageId() {
        return messageId;
    }

    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group aGroup) {
        this.group = aGroup;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
