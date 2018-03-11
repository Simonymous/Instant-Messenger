package im.core;

import rest.services.UserRestClientImpl;

import java.util.List;

public class OwnUser {
    private static OwnUser ownUser = null;

    private int userId;
    private String username;
    private String password;
    private boolean isCreated = false;

    private OwnUser() {

    }

    public void createUser(int id, String n, String pw) {
        if (!isCreated) {
            userId = id;
            username = n;
            password = pw;
            isCreated = true;
        }
    }

    public int getUserId() {
        return userId;
    }

    public String getUserStringId() {
        return String.format("%d", userId);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isCreated() {
        return isCreated;
    }

    public static OwnUser getInstance() {
        if (ownUser == null) {
            ownUser = new OwnUser();
        }
        return ownUser;
    }
}
