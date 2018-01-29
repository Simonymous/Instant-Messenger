package model.classes;

import model.interfaces.Group_User;

public class Group_UserImpl implements Group_User {
    int userId;
    int groupId;

    public Group_UserImpl() {
    }

    public Group_UserImpl(int userId, int groupId) {
        this.setUserId(userId);
        this.setGroupId(groupId);
    }

    @Override
    public int getUserId() {
        return userId;
    }

    @Override
    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int getGroupId() {
        return groupId;
    }

    @Override
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
