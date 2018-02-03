package dao.interfaces;

import model.interfaces.Group;
import model.interfaces.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GroupDao {
    void addNewGroup(Group aGroup);
    void removeGroup(Group aGroup);

    Group getGroupById(Group aGroup);
    ArrayList<Group> getAllGroups();

    void changeGroupName(Group aGroup);
}
