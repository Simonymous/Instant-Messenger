package dao.interfaces;

import model.interfaces.User;

import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    void addNewUser( User aUser );
    void removeUser( User aUser );

    void changeUsername( User aUser );
    void changePasword( User aUser );
    void changeActive( User aUser );

    List<User> getUsersFromDB();

    User getUserById( User aUser );
    User getUserByName( User aUser);
}
