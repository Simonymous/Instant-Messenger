package model.classes;

import model.interfaces.User;

import javax.persistence.*;

//TODO Annotationen hinzufügen
@Entity
@Table(name = "user")
public class UserImpl implements User {
    private int userId;
    private String username;
    private String password;
    private char active;


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
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

    public char getActive() {
        return active;
    }

    public void setActive(char active) {
        this.active = active;
    }
}
