package im.model.classes;

import im.model.exceptions.UserDoesNotExistException;
import im.model.interfaces.Group;
import im.model.interfaces.User;

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

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void addUser(User aUser){
        users.add(aUser);
    }

    public void removeUser(User aUser){
        if(users.isEmpty()){
            throw new UserDoesNotExistException("ERR_USER_DOES_NOT_EXIST");
        }else {
            users.remove(aUser);
        }
    }

    public ArrayList<User> getUsers(){
        return users;
    }
}
