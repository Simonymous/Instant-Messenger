package service.classes;

import builder.DaoObjectBuilder;
import builder.ModelObjectBuilder;
import dao.interfaces.GroupDao;
import dao.interfaces.Group_UserDao;
import model.interfaces.Group;
import model.interfaces.User;
import service.interfaces.GroupService;

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

    public void addUserToGroup(int id, String username) {
        //TODO: Implentierung
    }

    public void addUserToGroup(int userId, int id) {
        //TODO: Implentierung
    }

    public void addUserToGroup(int id, User user) {
        //TODO: Implentierung
    }

    public void removeUserFromGroup(int id, String username) {
        //TODO: Implentierung
    }
    public void removeUserFromGroup(int id, int userId) {
        //TODO: Implentierung
    }

    public void removeGroup(int id) {
        //TODO: GroupDoesNotExistException
        groupDao.removeGroup(getGroupDao(id));
    }

    public Group getGroupById(int id) {
        //TODO: GroupDoesNotExistException
        return groupDao.getGroupById(getGroupDao(id));
    }

    public void changeGroupName(int id, String newName) {
        //TODO: GroupDoesNotExistException
        Group group = getGroupDao(id);
        group.setGroupName(newName);
        groupDao.changeGroupName(group);
    }

   public ArrayList<User> getUsersForGroup(int id) {
       //TODO: Implentierung
        return null;
    }
    public ArrayList<Integer> getUserIdsForGroup(int id) {
        //TODO: Implentierung
        return null;
    }
    public ArrayList<String> getUserNamesForGroup(int id) {
        //TODO: Implentierung
        return null;
    }

    private Group getGroupDao(int id) {
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        return groupDao.getGroupById(group);
    }
}
