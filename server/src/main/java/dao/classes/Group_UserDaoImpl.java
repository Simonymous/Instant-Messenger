package dao.classes;


import builder.DaoObjectBuilder;
import builder.ModelObjectBuilder;
import dao.interfaces.GroupDao;
import dao.interfaces.Group_UserDao;
import model.interfaces.Group;
import model.interfaces.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.constants.GlobalConstants.*;

import static dao.constants.GroupDaoConstants.COL_GROUP_ID;
import static dao.constants.GroupDaoConstants.COL_GROUP_NAME;
import static dao.constants.GroupDaoConstants.TABLE_GROUP;
import static dao.constants.Group_UserDaoConstants.*;
import static dao.constants.UserDaoConstants.*;

public class Group_UserDaoImpl implements Group_UserDao{
    //------------------------------------------------------------------------------------------------------------------
// Prepared Statement Begin
//----------------------------------------------------------------------------------------------------------------------

    /**
     * INSERT INTO Group_User (userId, GroupId)
     * VALUES (?,?)
     */
    private static final String PS_ADD_NEW_USER_TO_GROUP = "INSERT INTO " + TABLE_GROUP_USERS
            + "(" + COL_USER_USER_ID + "," + COL_GROUP_GROUP_ID + ") VALUES (?,?)";

    /**
     * DELETE FROM Group_User
     * WHERE user_id = ?
     * AND group_id = ?
     */
    private static final String PS_REMOVE_USER_FROM_GROUP = "DELETE FROM " + TABLE_GROUP_USERS + " WHERE " +
            COL_USER_USER_ID + " = ?" + " AND " + COL_GROUP_GROUP_ID + " = ? ";

    /**
     *SELECT * FROM Group_User JOIN chatGroup
     *WHERE Group_User.userUserId = ?
     *AND chatGroup.groupId = Group_User.groupGroupId
     */
    private static final String PS_GET_GROUPS_BY_USER = "SELECT " + "*" + " FROM " + TABLE_GROUP_USERS + " JOIN "
            + TABLE_GROUP + " WHERE " + TABLE_GROUP_USERS + "." + COL_USER_USER_ID + " = ?" + " AND "
            + TABLE_GROUP + "." + COL_GROUP_ID + " = " + TABLE_GROUP_USERS + "." + COL_GROUP_GROUP_ID;

    /**
     *SELECT * FROM Group_User JOIN User
     *WHERE Group_User.groupGroupId = ?
     *AND User.userId = Group_User.userUserId
     */
    private static final String PS_GET_USERS_BY_GROUP = "SELECT " + "*" + " FROM " + TABLE_GROUP_USERS + " JOIN "
            + TABLE_USER + " WHERE " + TABLE_GROUP_USERS + "." + COL_GROUP_GROUP_ID + " = ? " + " AND "
            + TABLE_USER + "." + COL_USER_ID + " = " + TABLE_GROUP_USERS + "." + COL_USER_USER_ID;

//----------------------------------------------------------------------------------------------------------------------
// Prepared Statement End
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void addNewUser(User aUser, Group aGroup) {
        try (Connection connection = getConnection();

             PreparedStatement statement = connection.prepareStatement(PS_ADD_NEW_USER_TO_GROUP)) {
            statement.setInt(PARAMETER_1, aUser.getUserId());
            statement.setInt(PARAMETER_2, aGroup.getGroupId() );
            statement.executeUpdate();
        }
        catch (SQLException e){
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
        }
        catch (SQLException e){
            System.err.println(ERR_REMOVE_USER_FROM_GROUP);
        }
    }

    @Override
    public ArrayList<Group> getGroupsByUser(User aUser) {
        ArrayList<Group> groupList = new ArrayList<>();
        int id = aUser.getUserId();
        ResultSet rs;
        Group aGroup;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_GROUPS_BY_USER)) {
            statement.setInt(PARAMETER_1, id);
            rs = statement.executeQuery();

            while(rs.next()) {
                aGroup = ModelObjectBuilder.getGroupObject();
                aGroup.setGroupName(rs.getString(COL_GROUP_NAME));
                aGroup.setGroupId(rs.getInt(COL_GROUP_ID));

                groupList.add(aGroup);
            }


        }
        catch (SQLException e) {
            System.err.println(ERR_MSG_GET_GROUP_FROM_DB);
            e.printStackTrace();
        }

        return groupList;
    }

    @Override
    public ArrayList<User> getUsersByGroup(Group aGroup) {
        ArrayList<User> userList = new ArrayList<>();
        int id = aGroup.getGroupId();
        ResultSet rs;
        User aUser;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_USERS_BY_GROUP)) {
            statement.setInt(PARAMETER_1, id);
            rs = statement.executeQuery();

            while(rs.next()) {
                aUser = ModelObjectBuilder.getUserObject();
                aUser.setUserId(rs.getInt(COL_USER_ID));
                aUser.setUsername(rs.getString(COL_USER_USERNAME));
                aUser.setPassword(rs.getString(COL_USER_PASSWORD));
                aUser.setActive(rs.getBoolean(COL_USER_ACTIVE));

                userList.add(aUser);
            }

        }
        catch (SQLException e) {
            System.err.println(ERR_MSG_GET_USERS_FROM_DB);
            e.printStackTrace();
        }

        return userList;
    }

    public boolean doesGroupExist(int id) throws SQLException{
        GroupDao groupDao = DaoObjectBuilder.getGroupDaoObject();
        Group aNewGroup = ModelObjectBuilder.getGroupObject(id);
        for (Group group : groupDao.getAllGroups()) {
            if(group.getGroupId() == aNewGroup.getGroupId()) {
                return true;
            }
        }
        return false;
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
