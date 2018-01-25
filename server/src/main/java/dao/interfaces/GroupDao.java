package dao.interfaces;

import model.interfaces.Group;
import model.interfaces.User;

import java.util.ArrayList;

public interface GroupDao {
    public void save(Group group);
    public void delete(Group group);
    public Group getGroup(int groupId);
    public ArrayList<Group> getGroupsForUser(int userId);
}
