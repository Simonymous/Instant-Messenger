package service.interfaces;

import model.interfaces.Group;
import model.interfaces.User;
import rest.exceptions.*;

import java.util.ArrayList;

public interface GroupService {
    Group addNewGroup(String name);
    void addUserToGroup(int id, int userId) throws GroupDoesNotExistException, UserDoesNotExistException;
    void addUserToGroup(int id, String userName) throws GroupDoesNotExistException, UserDoesNotExistException;
    void addUserToGroup(int id, User user) throws GroupDoesNotExistException, UserDoesNotExistException;
    void removeUserFromGroup(int id, int userId) throws GroupDoesNotExistException, UserDoesNotExistException;
    void removeGroup(int id) throws GroupDoesNotExistException;
    void changeGroupName(int id, String newName) throws GroupDoesNotExistException;
    boolean doesGroupExist(int id);
    Group getGroupById(int id) throws GroupDoesNotExistException;
    ArrayList<User> getUsersForGroup(int id) throws GroupDoesNotExistException;
    ArrayList<Integer> getUserIdsForGroup(int id) throws GroupDoesNotExistException;
    ArrayList<String> getUserNamesForGroup(int id) throws GroupDoesNotExistException;
}
