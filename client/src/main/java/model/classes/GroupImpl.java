package model.classes;

import model.interfaces.Group;

public class GroupImpl implements Group {
    private int groupId;
    private String groupName;

    public GroupImpl() {
    }

    public GroupImpl(int groupId) {
        this.setGroupId(groupId);
    }

    public GroupImpl(String groupName) {
        this.setGroupName(groupName);
    }

    public GroupImpl(int groupId, String groupName) {
        this.setGroupId(groupId);
        this.setGroupName(groupName);
    }

    @Override
    public int getGroupId() {
        return groupId;
    }

    @Override
    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
