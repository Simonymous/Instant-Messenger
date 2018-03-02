package service.classes;

import builder.DaoObjectBuilder;
import builder.ModelObjectBuilder;
import dao.interfaces.GroupDao;
import dao.interfaces.Group_UserDao;
import dao.interfaces.UserDao;
import model.interfaces.Group;
import model.interfaces.User;
import rest.exceptions.*;
import service.interfaces.GroupService;

import static service.constants.ServiceConstants.*;

import java.util.ArrayList;

public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;
    private Group_UserDao group_userDao;
    private UserDao userDao;

    public GroupServiceImpl() {
        groupDao = DaoObjectBuilder.getGroupDaoObject();
        group_userDao = DaoObjectBuilder.getGroup_UserDaoObject();
        userDao = DaoObjectBuilder.getUserDaoObject();
    }

    public Group addNewGroup(String name) {
        Group group = ModelObjectBuilder.getGroupObject(name);
        groupDao.addNewGroup(group);
        return groupDao.getGroupByName(name);
    }

    public void addUserToGroup(int id, int userId) throws GroupDoesNotExistException, UserDoesNotExistException{
        User user = ModelObjectBuilder.getUserObject();
        user.setUserId(userId);
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        if(!doesUserExist(userId)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        group_userDao.addNewUser(user, group);
    }

    public void addUserToGroup(int groupId, String userName) throws GroupDoesNotExistException, UserDoesNotExistException{
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(groupId);
        if(!doesGroupExist(groupId)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        if(!doesUserExist(userName)) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        User user = ModelObjectBuilder.getUserObject();
        user.setUsername(userName);
        user = userDao.getUserByName(userName);
        group_userDao.addNewUser(user, group);
    }

    public void addUserToGroup(int id, User user) throws GroupDoesNotExistException, UserDoesNotExistException{
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        if(!doesUserExist(user.getUserId())) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        group_userDao.addNewUser(user, group);
    }

    public void removeUserFromGroup(int id, int userId) throws GroupDoesNotExistException, UserDoesNotExistException{
        User user = ModelObjectBuilder.getUserObject();
        user.setUserId(userId);
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        if(!doesUserExist(user.getUserId())) {
            throw new UserDoesNotExistException(ERR_MSG_USER_DOES_NOT_EXIST);
        }
        group_userDao.removeUser(user, group);
    }

    public void removeGroup(int id) throws GroupDoesNotExistException{
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        groupDao.removeGroup(getGroupDao(id));
    }

    public Group getGroupById(int id) throws GroupDoesNotExistException{
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        return getGroupDao(id);
    }

    public void changeGroupName(int id, String newName) throws GroupDoesNotExistException{
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        Group group = getGroupDao(id);
        group.setGroupName(newName);
        groupDao.changeGroupName(group);
    }

   public ArrayList<User> getUsersForGroup(int id) throws GroupDoesNotExistException{
       if(!doesGroupExist(id)) {
           throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
       }
        return group_userDao.getUsersByGroup(getGroupDao(id));
    }
    public ArrayList<Integer> getUserIdsForGroup(int id) throws GroupDoesNotExistException{
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        ArrayList<User> usersInGroup = getUsersForGroup(id);
        ArrayList<Integer> userIds = new ArrayList<>();
        for (User u : usersInGroup) {
            userIds.add(u.getUserId());
        }
        return userIds;
    }
    public ArrayList<String> getUserNamesForGroup(int id) throws GroupDoesNotExistException{
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        ArrayList<User> usersInGroup = getUsersForGroup(id);
        ArrayList<String> userNames = new ArrayList<>();
        for (User u : usersInGroup) {
            userNames.add(u.getUsername());
        }
        return userNames;
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

    //TODO Methode auslagern
    public boolean doesGroupExist(int id) {
        for (Group group : groupDao.getAllGroups()) {
            if(group == null){
                return false;
            }
            if(group.getGroupId() == id) {
                return true;
            }
        }
        return false;
    }

    private Group getGroupDao(int id) {
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        return groupDao.getGroupById(group);
    }

}
