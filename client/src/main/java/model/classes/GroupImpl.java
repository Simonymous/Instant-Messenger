package model.classes;

import model.interfaces.Group;

/**
 * class to hold a group id and group name
 */
public class GroupImpl implements Group {
    private int groupId;
    private String groupName;

    /**
     * default constructor
     */
    public GroupImpl() {
    }

    /**
     * constructor with given group id
     * @param groupId
     */
    public GroupImpl(int groupId) {
        this.setGroupId(groupId);
    }

    /**
     * constructor with given group name
     * @param groupName
     */
    public GroupImpl(String groupName) {
        this.setGroupName(groupName);
    }

    /**
     * constructor with given group id and group name
     * @param groupId
     * @param groupName
     */
    public GroupImpl(int groupId, String groupName) {
        this.setGroupId(groupId);
        this.setGroupName(groupName);
    }

    /**
     * method to get the group id
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

    /**
     * method to get group name
     * @return groupName
     */
    @Override
    public String getGroupName() {
        return groupName;
    }

    /**
     * method to set group name
     * @param groupName
     */
    @Override
    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

}
