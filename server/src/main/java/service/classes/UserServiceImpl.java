package service.classes;

import builder.DaoObjectBuilder;
import builder.ModelObjectBuilder;
import dao.interfaces.UserDao;
import model.interfaces.Group;
import model.interfaces.User;
import service.exceptions.UserAlreadyExistsException;
import service.exceptions.UserDoesNotExistException;
import service.interfaces.UserService;

import java.util.ArrayList;
import java.util.List;

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
          throw new UserAlreadyExistsException(ERR_MSG_USER_ALREADY_EXISTS);
      }
      if(!doesUserExist(oldUsername)) {
          throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
      }
      User user = getUserDao(oldUsername);
      user.setUsername(newUsername);
      userDao.changeUsername(user);
    }

    public void changeUserName(String newUsername, int id) {
        if(!doesUserExist(newUsername)) {
            throw new UserAlreadyExistsException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        User user = getUserDao(id);
        user.setUsername(newUsername);
        userDao.changeUsername(user);
    }

    public void changeUserPassword(String username, String password) {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        User user = getUserDao(username);
        user.setPassword(password);
        userDao.changePasword(user);
    }

    public void changeUserPassword(int id,String password) {
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        User user = getUserDao(id);
        user.setPassword(password);
        userDao.changePasword(user);
    }

    public boolean validCredentials(String username, String password) {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        return getUserDao(username).getPassword().equals(password);
    }

    public int getUserId(String username) {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        return getUserDao(username).getUserId();
    }

    public void addUser(String username, String password) {
        if(!doesUserExist(username)) {
            throw new UserAlreadyExistsException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        User user = ModelObjectBuilder.getUserObject();
        user.setUsername(username);
        user.setPassword(password);
        userDao.addNewUser(user);
    }

    public void removeUser(String username) {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        userDao.removeUser(getUserDao(username));
    }

    public void removeUser(int id) {
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        userDao.removeUser(getUserDao(id));
    }

    public boolean getStatusForUser(String username) {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        return getUserDao(username).getActive();
    }

    public boolean getStatusForUser(int id) {
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        return getUserDao(id).getActive();
    }

    public void setStatusForUser(String username, boolean b) {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        User user = getUserDao(username);
        user.setActive(b);
        userDao.changeActive(user);
    }

    public void setStatusForUser(int id, boolean b) {
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        User user = getUserDao(id);
        user.setActive(b);
        userDao.changeActive(user);
    }

    public ArrayList<Group> getGroupsForUser(String username) {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        User user = getUserDao(username);
        //TODO: Implentierung
        return null;
    }

    public ArrayList<Group> getGroupsForUser(int id) {
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        User user = getUserDao(id);
        //TODO: Implentierung
        return null;
    }

    private User getUserDao(String username) {
        User user = ModelObjectBuilder.getUserObject();
        user.setUsername(username);
        return userDao.getUserByName(user);
    }

    private User getUserDao(int id) {
        User user = ModelObjectBuilder.getUserObject();
        user.setUserId(id);
        return userDao.getUserById(user);
    }
}
