package im.model.classes;

import im.model.interfaces.Group_User;

public class Group_UserImpl implements Group_User {
    int userId;
    int groupId;

    public Group_UserImpl() {
    }

    public Group_UserImpl(int userId, int groupId) {
        this.setUserId(userId);
        this.setGroupId(groupId);
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }
}
