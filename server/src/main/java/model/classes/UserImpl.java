package model.classes;

import model.interfaces.User;

public class UserImpl implements User {
    private int userId;
    private String username;
    private String password;
    private boolean active;

    public UserImpl(){

    }

    public UserImpl(String username, String password){
        this.username = username;
        this.password = password;
    }

    @Override
    public int getUserId(){
        return userId;
    }

    @Override
    public void setUserId(int id){
        userId = id;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean getActive() {
        return active;
    }

    @Override
    public void setActive(boolean b) {
        this.active = b;
    }
}
