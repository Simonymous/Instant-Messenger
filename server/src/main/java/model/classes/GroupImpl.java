package model.classes;

import model.exceptions.UserDoesNotExistException;
import model.interfaces.Group;
import model.interfaces.User;

import java.util.ArrayList;

public class GroupImpl implements Group{
    private int groupId;
    private String groupName;
    private ArrayList<User> users;

    public GroupImpl(){
    }

    public GroupImpl(int groupId) {
        this.setGroupId(groupId);
    }

    public GroupImpl(String groupName){
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

    @Override
    public void addUser(User aUser){
        users.add(aUser);
    }

    @Override
    public void removeUser(User aUser){
        if(users.isEmpty()){
            throw new UserDoesNotExistException("ERR_USER_DOES_NOT_EXIST");
        }else {
            users.remove(aUser);
        }
    }

    @Override
    public ArrayList<User> getUsers(){
        return users;
    }
}
