package dao.classes;

import builder.ModelObjectBuilder;
import dao.enums.ColNameUser;
import dao.interfaces.UserDao;
import model.interfaces.User;

import static dao.constants.UserDaoConstants.*;
import static dao.constants.GlobalConstants.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//TODO Kommentare einfügen
public class UserDaoImpl implements UserDao{

    //------------------------------------------------------------------------------------------------------------------
// Prepared Statement Begin
//----------------------------------------------------------------------------------------------------------------------

    /**
     * INSERT INTO (username, password)
     * VALUES (?,?)
     */
    private static final String PS_ADD_NEW_USER = "INSERT INTO " + TABLE_USER
            + "(" + COL_USER_USERNAME + "," + COL_USER_PASSWORD + ") VALUES (?,?)";

    /**
     * DELETE FROM user
     * WHERE id = ?
     */
    private static final String PS_REMOVE_USER = "DELETE FROM " + TABLE_USER + " WHERE " + COL_USER_ID + " = ?";

    /**
     * UPDATE user
     * SET $attribut = ?
     * WHERE id = ?
     */
    private static final String PS_CHANGE_ATTRIBUT = "UPDATE " + TABLE_USER + " SET $attribut = ? WHERE "
            + COL_USER_ID + " = ?";

    /**
     * SELECT *
     * FROM user
     */
    private static final String PS_GET_USER_FROM_DB = "SELECT * FROM " + TABLE_USER;

    /**
     * SELECT *
     * FROM user
     * WHERE id = ?
     */
    private static final String PS_GET_USER_BY_ID = "SELECT * FROM " + TABLE_USER + " WHERE "
            + COL_USER_ID + " = ?";

    /**
     * SELECT *
     * FROM user
     * WHERE name = ?
     */
    private static final String PS_GET_USER_BY_NAME = "SELECT * FROM " + TABLE_USER + " WHERE "
            + COL_USER_USERNAME + " = ?";

//----------------------------------------------------------------------------------------------------------------------
// Prepared Statement End
//----------------------------------------------------------------------------------------------------------------------

    /**
     * Method to store new user in DB
     *
     * @param aUser User to store in DB
     */
    public void addNewUser( User aUser ) throws SQLException{
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_ADD_NEW_USER)) {

            statement.setString(PARAMETER_1, aUser.getUsername());
            statement.setString(PARAMETER_2, aUser.getPassword());

            statement.executeUpdate();
        }
    }

    public void removeUser( User aUser ) throws SQLException{
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_REMOVE_USER)) {
            statement.setInt(PARAMETER_1, aUser.getUserId() );
            statement.executeUpdate();
        } catch (SQLException e){
            System.err.println(ERR_MSG_REMOVE_USER);
        }
    }

    /**
     * Method to change atribute in tabel
     * @param colName name of the colume
     * @param value value of the colume
     */
    private void changeAttribut(ColNameUser colName, User aUser, String value) throws SQLException{
        int id = aUser.getUserId();
        String preparedStatement = PS_CHANGE_ATTRIBUT.replace("$attribut", colName.getColumnName());

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(preparedStatement)) {

            statement.setString(PARAMETER_1, value);
            statement.setInt(PARAMETER_2, id);
            statement.executeUpdate();
        }
    }

    /**
     * Method to change atribute in tabel
     * @param colName name of the colume
     * @param value value of the colume
     */
    private void changeAttribut(ColNameUser colName, User aAuthor, Boolean value) throws SQLException{
        int id = aAuthor.getUserId();
        String preparedStatement = PS_CHANGE_ATTRIBUT.replace("$attribut", colName.getColumnName());

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(preparedStatement)) {

            statement.setBoolean(PARAMETER_1, value);
            statement.setInt(PARAMETER_2, id);
            statement.executeUpdate();

        }
    }

    public void changeUsername( User aUser ) throws SQLException{
        changeAttribut(ColNameUser.Username, aUser, aUser.getUsername());
    }

    public void changePasword( User aUser ) throws SQLException{
        changeAttribut(ColNameUser.Password, aUser, aUser.getPassword());
    }

    public void changeActive( User aUser ) throws SQLException{
        changeAttribut(ColNameUser.Active, aUser, aUser.getActive());
    }

    public List<User> getUsersFromDB() throws SQLException{
        List<User> userList = new ArrayList<>();
        ResultSet rs;
        User aUser;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_USER_FROM_DB)) {

            rs = statement.executeQuery();

            while(rs.next()) {
                aUser = ModelObjectBuilder.getUserObject();
                aUser.setUsername(rs.getString(COL_USER_USERNAME));
                aUser.setPassword(rs.getString(COL_USER_PASSWORD));
                aUser.setUserId(rs.getInt(COL_USER_ID));
                aUser.setActive(rs.getBoolean(COL_USER_ACTIVE));

                userList.add(aUser);
            }

        }

        return userList;
    }

    public User getUserById( User aUser ) throws SQLException{
        User aNewUser = ModelObjectBuilder.getUserObject();
        ResultSet rs;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_USER_BY_ID)) {

            statement.setInt(PARAMETER_1, aUser.getUserId());
            rs = statement.executeQuery();

            while(rs.next()) {
                aNewUser.setUsername(rs.getString(COL_USER_USERNAME));
                aNewUser.setPassword(rs.getString(COL_USER_PASSWORD));
                aNewUser.setUserId(rs.getInt(COL_USER_ID));
                aNewUser.setActive(rs.getBoolean(COL_USER_ACTIVE));
            }

        }

        return aNewUser;
    }
    public User getUserByName( User aUser ) throws SQLException{
        User aNewUser = ModelObjectBuilder.getUserObject();
        ResultSet rs;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_USER_BY_NAME)) {

            statement.setString(PARAMETER_1, aUser.getUsername());
            rs = statement.executeQuery();

            while(rs.next()) {
                aNewUser.setUsername(rs.getString(COL_USER_USERNAME));
                aNewUser.setPassword(rs.getString(COL_USER_PASSWORD));
                aNewUser.setUserId(rs.getInt(COL_USER_ID));
                aNewUser.setActive(rs.getBoolean(COL_USER_ACTIVE));
            }

        }

        return aNewUser;
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
