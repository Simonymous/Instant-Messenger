package service.interfaces;

import model.interfaces.Group;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserService {
    boolean doesUserExist(String username) throws SQLException;
    boolean doesUserExist(int id) throws SQLException;
    void changeUserName(String NewUsername, String OldUsername) throws SQLException;
    void changeUserName(String NewUsername, int id) throws SQLException;
    void changeUserPassword(String username, String password) throws SQLException;
    void changeUserPassword(int id, String password) throws SQLException;
    boolean validCredentials(String username, String password) throws SQLException;
    int getUserId(String username) throws SQLException;
    void addUser(String username, String password) throws SQLException;
    void removeUser(String username) throws SQLException;
    void removeUser(int id) throws SQLException;
    boolean getStatusForUser(String username) throws SQLException;
    boolean getStatusForUser(int id) throws SQLException;
    void setStatusForUser(String username, boolean b) throws SQLException;
    void setStatusForUser(int id, boolean b) throws SQLException;
    ArrayList<Group> getGroupsForUser(String username) throws SQLException;
    ArrayList<Group> getGroupsForUser(int id) throws SQLException;
    ArrayList<Integer> getGroupIdsForUser(String username) throws SQLException;
    ArrayList<Integer> getGroupIdsForUser(int id) throws SQLException;
    //TODO Nice to have: getCountOfMesssages(), getAllMessagesFromUser()
}
