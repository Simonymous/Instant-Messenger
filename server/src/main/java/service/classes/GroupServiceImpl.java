package service.classes;

import builder.DaoObjectBuilder;
import builder.ModelObjectBuilder;
import dao.interfaces.GroupDao;
import model.interfaces.Group;
import service.interfaces.GroupService;

public class GroupServiceImpl implements GroupService {

    private GroupDao groupDao;

    public GroupServiceImpl() {
        groupDao = DaoObjectBuilder.getGroupDaoObject();
    }

    public void addNewGroup(String name) {
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupName(name);
        groupDao.addNewGroup(group);
    }

    public void removeGroup(int id) {
        groupDao.removeGroup(getGroupDao(id));
    }
    
    public Group getGroupById(int id) {
        return groupDao.getGroupById(getGroupDao(id));
    }

    public void changeGroupName(int id, String newName) {
        Group group = getGroupDao(id);
        group.setGroupName(newName);
        groupDao.changeGroupName(group);
    }

    private Group getGroupDao(int id) {
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(id);
        return groupDao.getGroupById(group);
    }
}
