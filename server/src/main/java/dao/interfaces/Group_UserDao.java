package dao.interfaces;

import model.interfaces.Group;
import model.interfaces.Group_User;
import model.interfaces.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface Group_UserDao {
    void addNewUser(User aUser, Group aGroup) throws SQLException;
    void removeUser(User aUser, Group aGroup) throws SQLException;

    ArrayList<Group> getGroupsByUser(User aUser) throws SQLException;
    ArrayList<User> getUsersByGroup(Group aGroup) throws SQLException;
}
