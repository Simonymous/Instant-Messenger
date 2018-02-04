package service.interfaces;

import model.interfaces.Group;
import rest.exceptions.UserAlreadyExistsException;
import rest.exceptions.UserDoesNotExistException;

import java.util.ArrayList;

public interface UserService {
    boolean doesUserExist(String username);
    boolean doesUserExist(int id);
    void changeUserName(String NewUsername, String OldUsername) throws UserAlreadyExistsException, UserDoesNotExistException;
    void changeUserName(String NewUsername, int id) throws UserAlreadyExistsException, UserDoesNotExistException;
    void changeUserPassword(String username, String password) throws UserDoesNotExistException;
    void changeUserPassword(int id, String password) throws UserDoesNotExistException;
    boolean validCredentials(String username, String password);
    int getUserId(String username) throws UserDoesNotExistException;
    void addUser(String username, String password) throws UserAlreadyExistsException;
    void removeUser(String username) throws UserDoesNotExistException;
    void removeUser(int id) throws UserDoesNotExistException;
    boolean getStatusForUser(String username) throws UserDoesNotExistException;
    boolean getStatusForUser(int id) throws UserDoesNotExistException;
    void setStatusForUser(String username, boolean b) throws UserDoesNotExistException;
    void setStatusForUser(int id, boolean b) throws UserDoesNotExistException;
    ArrayList<Group> getGroupsForUser(String username) throws UserDoesNotExistException;
    ArrayList<Group> getGroupsForUser(int id) throws UserDoesNotExistException;
    ArrayList<Integer> getGroupIdsForUser(String username) throws UserDoesNotExistException;
    ArrayList<Integer> getGroupIdsForUser(int id) throws UserDoesNotExistException;
    //TODO Nice to have: getCountOfMesssages(), getAllMessagesFromUser()
}
