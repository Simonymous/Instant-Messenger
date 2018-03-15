package im.model.classes;

import im.model.interfaces.Group_User;

/**
 * class to hold user id and group id
 */
public class Group_UserImpl implements Group_User {
    int userId;
    int groupId;

    /**
     * default constructor
     */
    public Group_UserImpl() {
    }

    /**
     * constructor with given user id and group id
     * @param userId
     * @param groupId
     */
    public Group_UserImpl(int userId, int groupId) {
        this.setUserId(userId);
        this.setGroupId(groupId);
    }

    /**
     * method to get user id
     * @return userId
     */
    @Override
    public int getUserId() {
        return userId;
    }

    /**
     * method to set user id
     * @param userId
     */
    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * method to get group id
     * @return groupId
     */
    @Override
    public int getGroupId() {
        return groupId;
    }

    /**
     * method to set group id
     * @param groupId
     */
    @Override
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
    
}
