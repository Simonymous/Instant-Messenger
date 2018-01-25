package dao.interfaces;

import model.interfaces.User;

import java.util.ArrayList;

public interface Group_UserDao {
    public ArrayList<User> getMembers();
    public ArrayList<Integer> getUserIdS();
    public ArrayList<Integer> getGroupIdS();
}
