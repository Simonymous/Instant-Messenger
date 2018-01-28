package service.classes;

import model.interfaces.Group;

import java.util.ArrayList;

public class UserServiceImpl {
    public boolean doesUserExist(String username) {
        return true;
    }
    public boolean doesUserExist(int id) {
        return true;
    }
    public boolean validCredentials(String username, String password) {
        return true;
    }
    public int getUserId(String username) {
        return 0;
    }
    public void add(String username, String password) {

    }
    public void remove(String username) {

    }
    public void remove(int id) {

    }
    public boolean getStatus(String username) {
        return true;
    }
    public boolean getStatus(int id) {
        return true;
    }
    public ArrayList<Group> getGroups(String username) {
        return null;
    }
    public ArrayList<Group> getGroups(int id) {
        return null;
    }
}
