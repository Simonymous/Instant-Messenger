package service.interfaces;

import model.interfaces.Group;

import java.util.ArrayList;

public interface UserService {
    boolean doesUserExist(String username);
    boolean doesUserExist(int id);
    boolean validCredentials(String username, String password);
    int getUserId(String username);
    void add(String username, String password);
    void remove(String username);
    void remove(int id);
    boolean getStatus(String username);
    boolean getStatus(int id);

    ArrayList<Group> getGroups(String username);
    ArrayList<Group> getGroups(int id);

    //TODO Nice to have: getCountOfMesssages(), getAllMessagesFromUser()
}
