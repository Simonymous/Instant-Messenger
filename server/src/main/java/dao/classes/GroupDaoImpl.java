package dao.classes;


import builder.ModelObjectBuilder;
import dao.interfaces.GroupDao;
import model.interfaces.Group;
import model.interfaces.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.constants.GlobalConstants.*;
import static dao.constants.GroupDaoConstants.*;

public class GroupDaoImpl implements GroupDao{

    //------------------------------------------------------------------------------------------------------------------
// Prepared Statement Begin
//----------------------------------------------------------------------------------------------------------------------

    /**
     * INSERT INTO Group (groupname)
     * VALUES (?)
     */
    private static final String PS_ADD_NEW_GROUP = "INSERT INTO " + TABLE_GROUP
            + "(" + COL_GROUP_NAME + ") VALUES (?)";

    /**
     * DELETE FROM Group
     * WHERE id = ?
     */
    private static final String PS_REMOVE_GROUP = "DELETE FROM " + TABLE_GROUP + " WHERE " + COL_GROUP_ID + " = ?";

    /**
     * SELECT *
     * FROM group
     * WHERE id = ?
     */
    private static final String PS_GET_GROUP_BY_ID = "SELECT * FROM " + TABLE_GROUP + " WHERE "
            + COL_GROUP_ID + " = ?";

//----------------------------------------------------------------------------------------------------------------------
// Prepared Statement End
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void addNewGroup(Group aGroup) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_ADD_NEW_GROUP)) {

            statement.setString(PARAMETER_1, aGroup.getGroupName());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(ERR_MSG_ADD_NEW_GROUP);
            e.printStackTrace();
        }
    }

    @Override
    public void removeGroup(Group aGroup) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_REMOVE_GROUP)) {
            statement.setInt(PARAMETER_1, aGroup.getGroupId() );
            statement.executeUpdate();
        } catch (SQLException e){
            System.err.println(ERR_MSG_REMOVE_GROUP);
        }
    }


    @Override
    public Group getGroupById(Group aGroup) {
        Group aNewGroup = ModelObjectBuilder.getGroupObject();
        ResultSet rs;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_GROUP_BY_ID)) {

            statement.setInt(PARAMETER_1, aGroup.getGroupId());
            rs = statement.executeQuery();

            while(rs.next()) {
                aNewGroup.setGroupName(rs.getString(COL_GROUP_NAME));
                aNewGroup.setGroupId(rs.getInt(COL_GROUP_ID));
            }

        } catch (SQLException e) {
            System.err.println(ERR_MSG_GET_GROUP_FROM_DB);
            e.printStackTrace();
        }

        return aNewGroup;
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
