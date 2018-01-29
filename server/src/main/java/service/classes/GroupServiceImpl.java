package service.classes;

import builder.DaoObjectBuilder;
import builder.ModelObjectBuilder;
import dao.interfaces.GroupDao;
import dao.interfaces.Group_UserDao;
import model.interfaces.Group;
import model.interfaces.User;
import service.exceptions.GroupDoesNotExistException;
import service.interfaces.GroupService;

import static service.constants.ServiceConstants.ERR_MSG_GROUP_DOES_NOT_EXIST;

import java.util.ArrayList;

public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;
    private Group_UserDao group_userDao;

    public GroupServiceImpl() {
        groupDao = DaoObjectBuilder.getGroupDaoObject();
        group_userDao = DaoObjectBuilder.getGroup_UserDaoObject();
    }

    public void addNewGroup(String name) {
        Group group = ModelObjectBuilder.getGroupObject(name);
        groupDao.addNewGroup(group);
    }

    public void addUserToGroup(int id, int userId) {
        User user = ModelObjectBuilder.getUserObject();
        user.setUserId(userId);
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        group_userDao.addNewUser(user, group);
    }

    public void addUserToGroup(int id, User user) {
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        group_userDao.addNewUser(user, group);
    }

    public void removeUserFromGroup(int id, int userId) {
        User user = ModelObjectBuilder.getUserObject();
        user.setUserId(userId);
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        group_userDao.removeUser(user, group);
    }

    public void removeGroup(int id) {
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        groupDao.removeGroup(getGroupDao(id));
    }

    public Group getGroupById(int id) {
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        return getGroupDao(id);
    }

    public void changeGroupName(int id, String newName) {
        if(!doesGroupExist(id)) {
            throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
        }
        Group group = getGroupDao(id);
        group.setGroupName(newName);
        groupDao.changeGroupName(group);
    }

   public ArrayList<User> getUsersForGroup(int id) {
       if(!doesGroupExist(id)) {
           throw new GroupDoesNotExistException(ERR_MSG_GROUP_DOES_NOT_EXIST);
       }
        return group_userDao.getUsersByGroup(getGroupDao(id));
    }
    public ArrayList<Integer> getUserIdsForGroup(int id) {
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
    public ArrayList<String> getUserNamesForGroup(int id) {
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

    public boolean doesGroupExist(int id) {
        return getGroupDao(id).equals(ModelObjectBuilder.getGroupObject());
    }

    private Group getGroupDao(int id) {
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        return groupDao.getGroupById(group);
    }

}
