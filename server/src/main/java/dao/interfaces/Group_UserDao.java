package dao.interfaces;

import model.interfaces.Group_User;
import model.interfaces.User;

import java.util.ArrayList;

public interface Group_UserDao {
    public void save(Group_User group_user);
    public ArrayList<User> getMembers();
    public ArrayList<Integer> getUserIdS();
    public ArrayList<Integer> getGroupIdS();
}
