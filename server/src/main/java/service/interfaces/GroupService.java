package service.interfaces;

import model.interfaces.Group;
import model.interfaces.User;

import java.util.ArrayList;

public interface GroupService {
    void addNewGroup(String name);
    void addUserToGroup(int id, int userId);
    void addUserToGroup(int id, User user);
    void removeUserFromGroup(int id, int userId);
    void removeGroup(int id);
    void changeGroupName(int id, String newName);
    boolean doesGroupExist(int id);
    Group getGroupById(int id);
    ArrayList<User> getUsersForGroup(int id);
    ArrayList<Integer> getUserIdsForGroup(int id);
    ArrayList<String> getUserNamesForGroup(int id);
}
