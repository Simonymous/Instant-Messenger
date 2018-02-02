package service.interfaces;

import model.interfaces.Group;
import model.interfaces.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GroupService {
    void addNewGroup(String name) throws SQLException;
    void addUserToGroup(int id, int userId) throws SQLException;
    void addUserToGroup(int id, User user) throws SQLException;
    void removeUserFromGroup(int id, int userId) throws SQLException;
    void removeGroup(int id) throws SQLException;
    void changeGroupName(int id, String newName) throws SQLException;
    boolean doesGroupExist(int id) throws SQLException;
    Group getGroupById(int id) throws SQLException;
    ArrayList<User> getUsersForGroup(int id) throws SQLException;
    ArrayList<Integer> getUserIdsForGroup(int id) throws SQLException;
    ArrayList<String> getUserNamesForGroup(int id) throws SQLException;
}
