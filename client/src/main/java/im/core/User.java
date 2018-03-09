package im.core;

import rest.services.UserRestClientImpl;

import java.util.List;

public class User {
    private static User user = null;

    private int userId;
    private String username;
    private String password;
    private boolean isCreated = false;

    private User() {

    }

    public boolean createUser(String name) throws Exception {
        if (!isCreated) {
            List<String> ids =
            new UserRestClientImpl().getUserByName(name).getIds();
            if (ids.size() > 1) throw new Exception("to many users with this name");
            userId = Integer.parseInt(ids.get(0));
            username = name;
            isCreated = true;
        }
        return isCreated;
    }

    public int getUserId(){
        return userId;
    }

    public String getUserStringId(){
        return String.format("%d",userId);
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

    public boolean isCreated(){
        return isCreated;
    }

    public static User getInstance() {
        if (user == null) {
            user = new User();
        }
        return user;
    }
}
