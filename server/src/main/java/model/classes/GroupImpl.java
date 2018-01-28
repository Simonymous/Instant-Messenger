package model.classes;

import model.interfaces.Group;
import model.interfaces.User;

import java.util.ArrayList;

public class GroupImpl implements Group{
    private int groupId;
    private String groupName;

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
