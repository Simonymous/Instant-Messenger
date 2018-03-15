package im.model.classes;

import im.model.interfaces.User;

/**
 * class to represent a user and hold user id, username,
 * password and it's current status
 */
public class UserImpl implements User {
    private int userId;
    private String username;
    private String password;
    private boolean active;

    /**
     * default constructor
     */
    public UserImpl() {
    }

    /**
     * constructor with given user id
     *
     * @param userId
     */
    public UserImpl(int userId) {
        this.setUserId(userId);
    }

    /**
     * constructor with given username and password
     *
     * @param username
     * @param password
     */
    public UserImpl(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }

    /**
     * constructor with given user id, username, password and status
     *
     * @param userId
     * @param username
     * @param password
     * @param active   (status)
     */
    public UserImpl(int userId, String username, String password, boolean active) {
        this.setUserId(userId);
        this.setUsername(username);
        this.setPassword(password);
        this.setActive(active);
    }

    /**
     * method to get user id
     *
     * @return userId
     */
    @Override
    public int getUserId() {
        return userId;
    }

    /**
     * method to set user id
     *
     * @param id
     */
    @Override
    public void setUserId(int id) {
        userId = id;
    }

    /**
     * method to get username
     *
     * @return username
     */
    @Override
    public String getUsername() {
        return username;
    }

    /**
     * method to set username
     *
     * @param username
     */
    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * method to get password
     *
     * @return password
     */
    @Override
    public String getPassword() {
        return password;
    }

    /**
     * method to set password
     *
     * @param password
     */
    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * method to get get current user status
     *
     * @return active (status)
     */
    @Override
    public boolean getActive() {
        return active;
    }

    /**
     * method to set current user status
     *
     * @param b
     */
    @Override
    public void setActive(boolean b) {
        this.active = b;
    }

}
