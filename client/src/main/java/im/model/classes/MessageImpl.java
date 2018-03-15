package im.model.classes;

import im.model.interfaces.Message;

/**
 * class to hold message id, group id, user id and message content
 */
public class MessageImpl implements Message {
    private long messageId;
    private int groupId;
    private int userId;
    private String content;

    /**
     * default constructor
     */
    public MessageImpl() {
    }

    /**
     * constructor with given message id
     * @param messageId
     */
    public MessageImpl(long messageId) {
        this.setMessageId(messageId);
    }

    /**
     * constructor with given user id and message content
     * @param userId
     * @param content
     */
    public MessageImpl(int userId, String content) {
        this.setContent(content);
        this.setUser(userId);
    }

    /**
     * constructor with given group id, user id and message content
     * @param groupId
     * @param userId
     * @param content
     */
    public MessageImpl(int groupId, int userId, String content) {
        this.setGroup(groupId);
        this.setUser(userId);
        this.setContent(content);
    }

    /**
     * method to get message id
     * @return messageId
     */
    @Override
    public long getMessageId() {
        return messageId;
    }

    /**
     * method to set message id
     * @param messageId
     */
    @Override
    public void setMessageId(long messageId) {
        this.messageId = messageId;
    }

    /**
     * method to get group id
     * @return groupId
     */
    @Override
    public int getGroup() {
        return groupId;
    }

    /**
     * method to set group id
     * @param groupId
     */
    @Override
    public void setGroup(int groupId) {
        this.groupId = groupId;
    }

    /**
     * method to get user id
     * @return userId
     */
    @Override
    public int getUser() {
        return userId;
    }

    /**
     * method to set user id
     * @param userId
     */
    @Override
    public void setUser(int userId) {
        this.userId = userId;
    }

    /**
     * method to get message content
     * @return content
     */
    @Override
    public String getContent() {
        return content;
    }

    /**
     * method to set message content
     * @param content
     */
    @Override
    public void setContent(String content) {
        this.content = content;
    }

}
