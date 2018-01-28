package service.classes;

import builder.DaoObjectBuilder;
import builder.ModelObjectBuilder;
import dao.interfaces.UserDao;
import model.classes.UserImpl;
import model.interfaces.Group;
import model.interfaces.User;
import service.exceptions.UserAlreadyExistsException;
import service.exceptions.UserDoesNotExistException;
import service.interfaces.UserService;

import java.util.ArrayList;

import static service.constants.UserServiceConstants.ERR_MSG_USER_ALREADY_EXISTS;
import static service.constants.UserServiceConstants.ERR_MSG_USER_DOES_NOT_EXIST;

public class UserServiceImpl implements UserService {
    private UserDao userDao;

    public UserServiceImpl() {
        userDao = DaoObjectBuilder.getUserDaoObject();
    }

    public boolean doesUserExist(String username) {
        for (User user : userDao.getUsersFromDB()) {
            if(user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }
    public boolean doesUserExist(int id) {
        for (User user : userDao.getUsersFromDB()) {
            if(user.getUserId() == id) {
                return true;
            }
        }
        return false;
    }
    public void changeUserName(String newUsername, String oldUsername) {
      if(!doesUserExist(newUsername)) {
          throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
      }
      if(!doesUserExist(oldUsername)) {
          throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
      }
      User user = ModelObjectBuilder.getUserObject();
      user.setUsername(oldUsername);
      user = userDao.getUserByName(user);
      user.setUsername(newUsername);
      userDao.changeUsername(user);

    }
    public void changeUserName(String NewUsername, int id) {
        if(!doesUserExist(NewUsername)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
    }
    public void changeUserPassword(String username, String password) {

    }
    public void changeUserPassword(int id,String password) {

    }
    public boolean validCredentials(String username, String password) {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        return true;
    }
    public int getUserId(String username) {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        return 0;
    }
    public void addUser(String username, String password) {
        if(!doesUserExist(username)) {
            throw new UserAlreadyExistsException(ERR_MSG_USER_ALREADY_EXISTS);
        }
    }
    public void removeUser(String username) {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
    }
    public void removeUser(int id) {
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
    }
    public boolean getStatusForUser(String username) {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        return true;
    }
    public boolean getStatusForUser(int id) {
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        return true;
    }
    public void setStatusForUser(String username) {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
    }
    public void setStatusForUser(int id) {
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
    }
    public ArrayList<Group> getGroupsForUser(String username) {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        return null;
    }
    public ArrayList<Group> getGroupsForUser(int id) {
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        return null;
    }
}
