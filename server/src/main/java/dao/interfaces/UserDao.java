package dao.interfaces;

import model.interfaces.User;

import java.util.ArrayList;

public interface UserDao {
    public void save(User user);
    public void delete(User user);
    public User getUser(int id);
    public User getUser(String Name);
    public ArrayList<User> getList();
}
