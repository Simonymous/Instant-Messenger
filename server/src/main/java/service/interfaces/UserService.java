package service.interfaces;

import model.interfaces.Group;

import java.util.ArrayList;

public interface UserService {
    boolean doesUserExist(String username);
    boolean doesUserExist(int id);
    void changeUserName(String username);
    void changeUserName(int id);
    void changeUserPassword(String username);
    void changeUserPassword(int id);
    boolean validCredentials(String username, String password);
    int getUserId(String username);
    void addUser(String username, String password);
    void removeUser(String username);
    void removeUser(int id);
    boolean getStatusForUser(String username);
    boolean getStatusForUser(int id);
    void setStatusForUser(String username);
    void setStatusForUser(int id);
    ArrayList<Group> getGroupsForUser(String username);
    ArrayList<Group> getGroupsForUser(int id);

    //TODO Nice to have: getCountOfMesssages(), getAllMessagesFromUser()
}
