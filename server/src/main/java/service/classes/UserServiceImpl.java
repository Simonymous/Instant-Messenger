package service.classes;

import builder.DaoObjectBuilder;
import dao.interfaces.UserDao;
import model.interfaces.Group;
import model.interfaces.User;
import service.interfaces.UserService;

import java.util.ArrayList;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = DaoObjectBuilder.getUserDaoObject();
    }

    public boolean doesUserExist(String username) {
        for (User user :userDao.getUsersFromDB()) {
            if(user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public boolean doesUserExist(int id) {
        return true;
    }
    public void changeUserName(String username) {

    }
    public void changeUserName(int id) {

    }
    public void changeUserPassword(String username) {

    }
    public void changeUserPassword(int id) {

    }
    public boolean validCredentials(String username, String password) {
        return true;
    }
    public int getUserId(String username) {
        return 0;
    }
    public void addUser(String username, String password) {

    }
    public void removeUser(String username) {

    }
    public void removeUser(int id) {

    }
    public boolean getStatusForUser(String username) {
        return true;
    }
    public boolean getStatusForUser(int id) {
        return true;
    }
    public void setStatusForUser(String username) {

    }
    public void setStatusForUser(int id) {

    }
    public ArrayList<Group> getGroupsForUser(String username) {
        return null;
    }
    public ArrayList<Group> getGroupsForUser(int id) {
        return null;
    }
}
