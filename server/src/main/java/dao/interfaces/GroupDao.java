package dao.interfaces;

import model.interfaces.User;

public interface GroupDao {
    public void changeName(String name);
    public String getName();
    public void addUser(User user);
    public void removeUser(User user);
}
