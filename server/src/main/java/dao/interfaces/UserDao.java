package dao.interfaces;

import model.interfaces.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface UserDao {
    void addNewUser( User aUser ) throws SQLException;
    void removeUser( User aUser ) throws SQLException;

    void changeUsername( User aUser ) throws SQLException;
    void changePasword( User aUser ) throws SQLException;
    void changeActive( User aUser ) throws SQLException;

    List<User> getUsersFromDB() throws SQLException;

    User getUserById( User aUser ) throws SQLException;
    User getUserByName( User aUser) throws SQLException;
}
