package service.classes;

import builder.DaoObjectBuilder;
import builder.ModelObjectBuilder;
import dao.interfaces.GroupDao;
import dao.interfaces.Group_UserDao;
import dao.interfaces.UserDao;
import model.interfaces.Group;
import model.interfaces.User;
import service.exceptions.GroupDoesNotExistException;
import service.interfaces.GroupService;

import static service.constants.ServiceConstants.ERR_MSG_GROUP_DOES_NOT_EXIST;

import java.sql.SQLException;
import java.util.ArrayList;

//TODO Throws Klausel zu Methoden hinzufügen die Exceptions werfen können
public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;
    private Group_UserDao group_userDao;
    private UserDao userDao;

    public GroupServiceImpl() throws SQLException {
        groupDao = DaoObjectBuilder.getGroupDaoObject();
        group_userDao = DaoObjectBuilder.getGroup_UserDaoObject();
        userDao = DaoObjectBuilder.getUserDaoObject();
    }

    public void addNewGroup(String name) throws SQLException{
        Group group = ModelObjectBuilder.getGroupObject(name);
        groupDao.addNewGroup(group);
    }

    public void addUserToGroup(int id, int userId) throws SQLException, GroupDoesNotExistException{
        User user = ModelObjectBuilder.getUserObject();
        user.setUserId(userId);
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        group_userDao.addNewUser(user, group);
    }

    public void addUserToGroup(int id, String userName) throws SQLException, GroupDoesNotExistException{
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        User user = ModelObjectBuilder.getUserObject();
        user.setUsername(userName);
        user = userDao.getUserByName(user);
        group_userDao.addNewUser(user, group);
    }

    public void addUserToGroup(int id, User user) throws SQLException, GroupDoesNotExistException{
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        group_userDao.addNewUser(user, group);
    }

    public void removeUserFromGroup(int id, int userId) throws SQLException, GroupDoesNotExistException{
        User user = ModelObjectBuilder.getUserObject();
        user.setUserId(userId);
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        group_userDao.removeUser(user, group);
    }

    public void removeGroup(int id) throws SQLException, GroupDoesNotExistException{
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        groupDao.removeGroup(getGroupDao(id));
    }

    public Group getGroupById(int id) throws SQLException, GroupDoesNotExistException{
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        return getGroupDao(id);
    }

    public void changeGroupName(int id, String newName) throws SQLException, GroupDoesNotExistException{
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        Group group = getGroupDao(id);
        group.setGroupName(newName);
        groupDao.changeGroupName(group);
    }

   public ArrayList<User> getUsersForGroup(int id) throws SQLException, GroupDoesNotExistException{
       if(!doesGroupExist(id)) {
           throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
       }
        return group_userDao.getUsersByGroup(getGroupDao(id));
    }
    public ArrayList<Integer> getUserIdsForGroup(int id) throws SQLException, GroupDoesNotExistException{
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        ArrayList<User> usersInGroup = getUsersForGroup(id);
        ArrayList<Integer> userIds = null;
        for (User u : usersInGroup) {
            userIds.add(u.getUserId());
        }
        return userIds;
    }
    public ArrayList<String> getUserNamesForGroup(int id) throws SQLException, GroupDoesNotExistException{
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        ArrayList<User> usersInGroup = getUsersForGroup(id);
        ArrayList<String> userNames = null;
        for (User u : usersInGroup) {
            userNames.add(u.getUsername());
        }
        return userNames;
    }

    public boolean doesGroupExist(int id) throws SQLException{
        for (Group group : groupDao.getAllGroups()) {
            if(group.getGroupId() == id) {
                return true;
            }
        }
        return false;
    }

    private Group getGroupDao(int id) throws SQLException{
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        return groupDao.getGroupById(group);
    }

}
