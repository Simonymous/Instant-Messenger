package service.classes;

import builder.DaoObjectBuilder;
import builder.ModelObjectBuilder;
import com.google.common.hash.Hashing;
import dao.interfaces.Group_UserDao;
import dao.interfaces.UserDao;
import model.classes.UserQueryResponseImpl;
import model.interfaces.Group;
import model.interfaces.User;
import rest.exceptions.*;
import service.interfaces.UserService;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static service.constants.ServiceConstants.ERR_MSG_USER_ALREADY_EXISTS;
import static service.constants.ServiceConstants.ERR_MSG_USER_DOES_NOT_EXIST;

public class UserServiceImpl implements UserService {
    private UserDao userDao;
    private Group_UserDao group_userDao;

    public UserServiceImpl() {
        userDao = DaoObjectBuilder.getUserDaoObject();
        group_userDao = DaoObjectBuilder.getGroup_UserDaoObject();
    }

    public boolean doesUserExist(String username) {
        for (User user : userDao.getUsersFromDB()) {
            if(user == null){
                return false;
            }
            if(user.getUsername().equals(username)) {
                return true;
            }
        }
        return false;
    }

    public boolean doesUserExist(int id) {
        for (User user : userDao.getUsersFromDB()) {
            if(user == null){
                return false;
            }
            if(user.getUserId() == id) {
                return true;
            }
        }
        return false;
    }

    public void changeUserName(String newUsername, String oldUsername) throws UserAlreadyExistsException, UserDoesNotExistException {
      if(doesUserExist(newUsername)) {
          throw new UserAlreadyExistsException(ERR_MSG_USER_ALREADY_EXISTS);
      }
      if(!doesUserExist(oldUsername)) {
          throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
      }
      User user = getUserDao(oldUsername);
      user.setUsername(newUsername);
      userDao.changeUsername(user);
    }

    public void changeUserName(String newUsername, int id) throws UserAlreadyExistsException, UserDoesNotExistException{
        if(doesUserExist(newUsername)) {
            throw new UserAlreadyExistsException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        User user = getUserDao(id);
        user.setUsername(newUsername);
        userDao.changeUsername(user);
    }

    public void changeUserPassword(String username, String password) throws UserDoesNotExistException{
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        User user = getUserDao(username);
        user.setPassword(getHash(password));
        userDao.changePasword(user);
    }

    public void changeUserPassword(int id,String password) throws UserDoesNotExistException{
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        User user = getUserDao(id);
        user.setPassword(getHash(password));
        userDao.changePasword(user);
    }

    public boolean validCredentials(String username, String password) {
        if(!doesUserExist(username)) {
            return false;
        }
        return getUserDao(username).getPassword().equals(getHash(password));
    }

    public int getUserId(String username) throws UserDoesNotExistException{
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        return getUserDao(username).getUserId();
    }

    public User getUserById(int username) throws UserDoesNotExistException{ // TODO change this
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        return getUserDao(username);
    }

    public User addUser(String username, String password) throws UserAlreadyExistsException{
        if(doesUserExist(username)) {
            throw new UserAlreadyExistsException(ERR_MSG_USER_ALREADY_EXISTS);
        }
        User user = ModelObjectBuilder.getUserObject(username, getHash(password));
        userDao.addNewUser(user);
        return userDao.getUserByName(username);
    }

    public void removeUser(String username) throws UserDoesNotExistException{
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        userDao.removeUser(getUserDao(username));
    }

    public void removeUser(int id) throws UserDoesNotExistException{
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        userDao.removeUser(getUserDao(id));
    }

    public boolean getStatusForUser(String username) throws UserDoesNotExistException{
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        return getUserDao(username).getActive();
    }

    public boolean getStatusForUser(int id) throws UserDoesNotExistException{
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        return getUserDao(id).getActive();
    }

    public void setStatusForUser(String username, boolean b) throws UserDoesNotExistException{
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        User user = getUserDao(username);
        user.setActive(b);
        userDao.changeActive(user);
    }

    public void setStatusForUser(int id, boolean b) throws UserDoesNotExistException{
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        User user = getUserDao(id);
        user.setActive(b);
        userDao.changeActive(user);
    }

    public ArrayList<Group> getGroupsForUser(String username) throws UserDoesNotExistException{
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        User user = getUserDao(username);
        return group_userDao.getGroupsByUser(user);
    }

    public ArrayList<Group> getGroupsForUser(int id) throws UserDoesNotExistException {
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        User user = getUserDao(id);
        return group_userDao.getGroupsByUser(user);
    }

    public ArrayList<Integer> getGroupIdsForUser(String username) throws UserDoesNotExistException {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        User user = getUserDao(username);
        ArrayList<Group> groupsForUser = getGroupsForUser(username);
        ArrayList<Integer> groupIds = new ArrayList<Integer>();
        for (Group g : groupsForUser) {
            groupIds.add(g.getGroupId());
        }
        return groupIds;
    }

    public ArrayList<Integer>getGroupIdsForUser(int id) throws UserDoesNotExistException {
        if(!doesUserExist(id)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        User user = getUserDao(id);
        ArrayList<Group> groupsForUser = getGroupsForUser(id);
        ArrayList<Integer> groupIds = new ArrayList<Integer>();
        for (Group g : groupsForUser) {
            groupIds.add(g.getGroupId());
        }
        return groupIds;
    }

    @Override
    public UserQueryResponseImpl getUsersByQuery(String qu) throws UserDoesNotExistException {
        List<String> l = new ArrayList<String>();
        for(User u : userDao.getUsersFromDB()) {
            if(qu.isEmpty() || u.getUsername().contains(qu)) {
                l.add(Integer.toString(u.getUserId()));
            }
        }
        UserQueryResponseImpl impl = new UserQueryResponseImpl();

        impl.setIds(l);
        return impl;
    }

    public User getUserByName(String username) throws UserDoesNotExistException {
        if(!doesUserExist(username)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        return getUserDao(username);
    }

    private User getUserDao(String username)  {
        return userDao.getUserByName(username);
    }

    private User getUserDao(int id) {
        // TODO ??
//        User user = ModelObjectBuilder.getUserObject();
//        user.setUserId(id);
        return userDao.getUserById(id);
    }

    private String getHash(String password){
        String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        return hashedPassword;
    }
}
