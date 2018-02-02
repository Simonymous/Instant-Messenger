package dao.interfaces;

import model.interfaces.Group;
import model.interfaces.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface GroupDao {
    void addNewGroup(Group aGroup) throws SQLException;
    void removeGroup(Group aGroup) throws SQLException;

    Group getGroupById(Group aGroup) throws SQLException;

    void changeGroupName(Group aGroup) throws SQLException;
}
