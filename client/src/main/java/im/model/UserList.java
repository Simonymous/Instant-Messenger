package im.model;

import im.core.OwnUser;
import im.model.interfaces.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Class represents all User of the Messenger
 */
public class UserList {
    private static UserList userList = null;

    private final ObservableList<User> serverUsers = FXCollections.observableArrayList();

    /**
     * standard constructor
     */
    private UserList() {
    }

    /**
     * add the given user if it's not in list
     *
     * @param u the user to add
     */
    public void addUser(User u) {
        if (u.getUserId() != OwnUser.getInstance().getUserId() && !containsUser(u))
            serverUsers.add(u);
    }

    /**
     * deletes the given user from list
     *
     * @param u the user to delete
     */
    public void deleteUser(User u) {
        serverUsers.remove(u);
    }

    /**
     * deletes a user from list with given id
     *
     * @param id the id of user for delete
     */
    public void deleteUser(int id) {
        for (User u : serverUsers) {
            if (u.getUserId() == id) {
                deleteUser(u);
            }
        }
    }

    /**
     * returns the user with given id
     *
     * @param id the id of user
     * @return the user with given id, null if it's not in list
     */
    public User getUser(int id) {
        for (User u : serverUsers) {
            if (u.getUserId() == id) return u;
        }
        return null;
    }

    /**
     * check if the given user is already in list
     *
     * @param u the user for check
     * @return is in list
     */
    public boolean containsUser(User u) {
        return serverUsers.contains(u);
    }

    /**
     * check if the user with given id is already in list
     *
     * @param id the id of user for check
     * @return is in list
     */
    public boolean containsUser(int id) {
        for (User u : serverUsers) {
            if (u.getUserId() == id) {
                return true;
            }
        }
        return false;
    }

    /**
     * gets the instance of the Singelton Object
     *
     * @return the instance
     */
    public static UserList getInstance() {
        if (userList == null) {
            userList = new UserList();
        }
        return userList;
    }

    public ObservableList<User> getServerUsers() {
        return serverUsers;
    }
}
