package dao.classes;


import builder.ModelObjectBuilder;
import dao.enums.ColNameGroup;
import dao.enums.ColNameUser;
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
    public void addNewGroup(Group aGroup) throws SQLException{
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_ADD_NEW_GROUP)) {

            statement.setString(PARAMETER_1, aGroup.getGroupName());

            statement.executeUpdate();
        }
    }

    @Override
    public void removeGroup(Group aGroup) throws SQLException{
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_REMOVE_GROUP)) {
            statement.setInt(PARAMETER_1, aGroup.getGroupId() );
            statement.executeUpdate();
        }
    }


    @Override
    public Group getGroupById(Group aGroup) throws SQLException{
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

        }

        return aNewGroup;
    }

    @Override
    public void changeGroupName(Group aGroup) throws SQLException{
        changeAttribut(ColNameGroup.GroupName, aGroup, aGroup.getGroupName());
    }

    /**
     * Method to change atribute in tabel
     * @param colName name of the colume
     * @param value value of the colume
     */
    private void changeAttribut(ColNameGroup colName, Group aGroup, String value) throws SQLException{
        int id = aGroup.getGroupId();
        String preparedStatement = PS_CHANGE_ATTRIBUT.replace("$attribut", colName.getColumnName());

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(preparedStatement)) {

            statement.setString(PARAMETER_1, value);
            statement.setInt(PARAMETER_2, id);
            statement.executeUpdate();

        }
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
