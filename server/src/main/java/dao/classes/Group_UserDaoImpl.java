package dao.classes;


import builder.ModelObjectBuilder;
import dao.interfaces.Group_UserDao;
import model.interfaces.Group;
import model.interfaces.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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
        List<Group> userList = new ArrayList<>();
        ResultSet rs;
        Group aGroup;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_GROUP_FROM_DB)) {

            rs = statement.executeQuery();

            while(rs.next()) {
                aUser = ModelObjectBuilder.getUserObject();
                aUser.setUsername(rs.getString(COL_USER_USERNAME));
                aUser.setPassword(rs.getString(COL_USER_PASSWORD));
                aUser.setUserId(rs.getInt(COL_USER_ID));
                aUser.setActive(rs.getBoolean(COL_USER_ACTIVE));

                userList.add(aUser);
            }

        } catch (SQLException e) {
            System.err.println(ERR_MSG_GET_GROUP_FROM_DB);
            e.printStackTrace();
        }

        return userList;
    }

    @Override
    public ArrayList<User> getUsersByGroup(Group aGroup) {
        return null;
    }

    /**
     * Method to connect to DB
     *
     * @return Connection Objekt
     */
    private Connection getConnection() {
        JDBCdatabaseConnection con = new JDBCdatabaseConnection();

        return con.getConnection();
    }

}
