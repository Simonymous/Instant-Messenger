package dao.classes;


import dao.interfaces.Group_UserDao;
import model.interfaces.Group;
import model.interfaces.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.constants.GlobalConstants.*;

import static dao.constants.Group_UserDaoConstants.*;

public class Group_UserDaoImpl implements Group_UserDao{
    @Override
    public void addNewUser(User aUser, Group aGroup) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_ADD_NEW_USER_TO_GROUP)) {
            statement.setInt(PARAMETER_1, aUser.getUserId());
            statement.setInt(PARAMETER_2, aGroup.getGroupId() );
            statement.executeUpdate();
        } catch (SQLException e){
            System.err.println(ERR_MSG_ADD_USER_TO_GROUP);
        }
    }

    @Override
    public void removeUser(User aUser, Group aGroup) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_REMOVE_USER_FROM_GROUP)) {
            statement.setInt(PARAMETER_1, aUser.getUserId());
            statement.setInt(PARAMETER_2, aGroup.getGroupId() );
            statement.executeUpdate();
        } catch (SQLException e){
            System.err.println(ERR_REMOVE_USER_FROM_GROUP);
        }
    }

    @Override
    public ArrayList<Group> getGroupsByUser(User aUser) {

    }

    @Override
    public ArrayList<User> getUsersByGroup(Group aGroup) {
        return null;
    }

}
