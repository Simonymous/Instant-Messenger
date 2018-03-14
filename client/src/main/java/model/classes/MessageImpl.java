package model.classes;

import model.interfaces.Message;

//TODO Eventuell Content als eigene Klasse
public class MessageImpl implements Message {
    private long messageId;
    private int groupId;
    private int userId;
    private String content;

    public MessageImpl() {
    }

    public MessageImpl(long messageId) {
        this.setMessageId(messageId);
    }

    public MessageImpl(int userId, String content) {
        this.setContent(content);
        this.setUser(userId);
    }

    public MessageImpl(int aGroup, int aUser, String content) {
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
    public int getGroup() {
        return groupId;
    }

    @Override
    public void setGroup(int aGroup) {
        this.groupId = aGroup;
    }

    @Override
    public int getUser() {
        return userId;
    }

    @Override
    public void setUser(int user) {
        this.userId = user;
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
