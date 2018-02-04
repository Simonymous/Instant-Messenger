package model.classes;

import model.interfaces.Group;
import model.interfaces.Message;
import model.interfaces.User;

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

    @Override
    public long getMessageId() {
        return messageId;
    }

    @Override
    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public void setGroup(Group aGroup) {
        this.group = aGroup;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String getContent() {
        return content;
    }

    @Override
    public void setContent(String content) {
        this.content = content;
    }


}
