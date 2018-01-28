package dao.interfaces;

import model.interfaces.Group;
import model.interfaces.Group_User;
import model.interfaces.User;

import java.util.ArrayList;

public interface Group_UserDao {
    void addNewUser(User aUser, Group aGroup);
    void removeUser(User aUser, Group aGroup);

    ArrayList<Group> getGroupsByUser(User aUser);
    ArrayList<User> getUsersByGroup(Group aGroup);
}
