package im.model.classes;

import im.model.interfaces.User;

public class UserImpl implements User {
    private int userId;
    private String username;
    private String password;
    private boolean active;

    public UserImpl(){

    }

    public UserImpl(int userId){
        this.setUserId(userId);
    }

    public UserImpl(String username, String password){
        this.setUsername(username);
        this.setPassword(password);
    }

    public int getUserId(){
        return userId;
    }

    public void setUserId(int id){
        userId = id;
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

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean b) {
        this.active = b;
    }
}
