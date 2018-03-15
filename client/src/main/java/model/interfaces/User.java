package model.interfaces;

/**
 * interface for user
 */
public interface User {
    
    int getUserId();

    void setUserId(int id);

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    boolean getActive();

    void setActive(boolean b);

}
