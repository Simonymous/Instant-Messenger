package im.model;

import im.core.OwnUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.interfaces.User;

public class UserList {

    private static UserList userList = null;
    ObservableList<User> serverUsers = FXCollections.observableArrayList();

    private UserList() {
    }

    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    public void addUser(User u) {
        if (u.getUserId() != OwnUser.getInstance().getUserId() && !containsUser(u))
            serverUsers.add(u);
    }

    public void deleteUser(User u) {
        serverUsers.remove(u);
    }

    public void deleteUser(int id) {
        for (User u : serverUsers) {
            if (u.getUserId() == id) {
                deleteUser(u);
            }
        }
    }

    public User getUser(int id) {
        for (User u : serverUsers) {
            if (u.getUserId() == id) return u;
        }
        return null;
    }

    public ObservableList<User> getServerUsers() {
        return serverUsers;
    }

    public boolean containsUser(User u) {
        return serverUsers.contains(u);
    }

    public boolean containsUser(int id) {
        for (User u : serverUsers) {
            if (u.getUserId() == id) {
                return true;
            }
        }
        return false;
    }
}
