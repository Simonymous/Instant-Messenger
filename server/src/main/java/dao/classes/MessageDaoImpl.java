package dao.classes;

import builder.ModelObjectBuilder;
import dao.interfaces.MessageDao;
import model.interfaces.Group;
import model.interfaces.Message;
import model.interfaces.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import static dao.constants.GlobalConstants.*;
import static dao.constants.GroupDaoConstants.COL_GROUP_ID;
import static dao.constants.GroupDaoConstants.TABLE_GROUP;
import static dao.constants.MessageDaoConstants.*;
import static dao.constants.UserDaoConstants.COL_USER_ID;
import static dao.constants.UserDaoConstants.TABLE_USER;

public class MessageDaoImpl implements MessageDao{

    //------------------------------------------------------------------------------------------------------------------
// Prepared Statement Begin
//----------------------------------------------------------------------------------------------------------------------

    /**
     * INSERT INTO Message (group, user, content)
     * VALUES (?,?,?)
     */
    private static final String PS_ADD_NEW_MESSAGE = "INSERT INTO " + TABLE_MESSAGE
            + "(" + COL_MESSAGE_GROUP_ID + "," + COL_MESSAGE_USER_ID + "," + COL_MESSAGE_CONTENT + ") VALUES (?,?,?)";

    /**
     * DELETE FROM Message
     * WHERE id = ?
     */
    private static final String PS_REMOVE_MESSAGE = "DELETE FROM " + TABLE_MESSAGE + " WHERE " + COL_MESSAGE_ID + " = ?";

    /**
     * SELECT *
     * FROM Message
     * WHERE id = ?
     */
    private static final String PS_GET_MESAGE_BY_ID = "SELECT * FROM " + TABLE_MESSAGE + " WHERE "
            + COL_MESSAGE_ID + " = ?";

    /**
     * SELECT *
     * FROM Message JOIN User
     * WHERE Message.userUserId = User.userId
     */
    public static final String PS_GET_MESAGES_BY_USER = "SELECT * FROM " + TABLE_MESSAGE + " JOIN " + TABLE_USER + " WHERE "
            + TABLE_MESSAGE + "." + COL_MESSAGE_USER_ID + " = " + TABLE_USER + " . " + COL_USER_ID;

    /**
     *
     */
    public static final String PS_GET_MESAGES_BY_GROUP = "SELECT * FROM " + TABLE_MESSAGE + " JOIN " + TABLE_GROUP + " WHERE "
            + TABLE_MESSAGE + "." + COL_MESSAGE_USER_ID + " = " + TABLE_GROUP + " . " + COL_GROUP_ID;


//----------------------------------------------------------------------------------------------------------------------
// Prepared Statement End
//----------------------------------------------------------------------------------------------------------------------

    @Override
    public void createMessage(Message aMessage) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_ADD_NEW_MESSAGE)) {

            statement.setInt(PARAMETER_1, aMessage.getGroup().getGroupId());
            statement.setInt(PARAMETER_2, aMessage.getUser().getUserId());
            statement.setString(PARAMETER_3, aMessage.getContent());

            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println(ERR_MSG_ADD_NEW_MESSAGE);
            e.printStackTrace();
        }
    }

    @Override
    public void deleteMessage(Message aMessage) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_REMOVE_MESSAGE)) {
            statement.setLong(PARAMETER_1, aMessage.getMessageId());
            statement.executeUpdate();
        } catch (SQLException e){
            System.err.println(ERR_MSG_REMOVE_MESSAGE);
        }
    }

    @Override
    public Message getMessageById(Message aMessage) {
        Message aNewMessage = ModelObjectBuilder.getMessageObject();
        ResultSet rs;
        Group aGroup;
        User aUser;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_MESAGE_BY_ID)) {

            statement.setLong(PARAMETER_1, aMessage.getMessageId());
            rs = statement.executeQuery();

            while(rs.next()) {
                aGroup = ModelObjectBuilder.getGroupObject();
                aUser = ModelObjectBuilder.getUserObject();

                aNewMessage.setMessageId(rs.getLong(COL_MESSAGE_ID));
                aGroup.setGroupId(rs.getInt(COL_MESSAGE_GROUP_ID));
                aNewMessage.setGroup(aGroup);
                aUser.setUserId(rs.getInt(COL_MESSAGE_USER_ID));
                aNewMessage.setUser(aUser);
                aNewMessage.setContent(rs.getString(COL_MESSAGE_CONTENT));
            }

        } catch (SQLException e) {
            System.err.println(ERR_MSG_GET_MESSAGE_FROM_DB);
            e.printStackTrace();
        }

        return aNewMessage;
    }

    @Override
    public ArrayList<Message> getMessagesByUser(Message aMessage) {
        Message aNewMessage = ModelObjectBuilder.getMessageObject();
        ArrayList<Message> messages = new ArrayList<Message>();
        ResultSet rs;
        Group aGroup;
        User aUser;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_MESAGES_BY_USER)) {

            statement.setInt(PARAMETER_1, aMessage.getUser().getUserId());
            rs = statement.executeQuery();

            while(rs.next()) {
                aGroup = ModelObjectBuilder.getGroupObject();
                aUser = ModelObjectBuilder.getUserObject();

                aNewMessage.setMessageId(rs.getLong(COL_MESSAGE_ID));
                aGroup.setGroupId(rs.getInt(COL_MESSAGE_GROUP_ID));
                aNewMessage.setGroup(aGroup);
                aUser.setUserId(rs.getInt(COL_MESSAGE_USER_ID));
                aNewMessage.setUser(aUser);
                aNewMessage.setContent(rs.getString(COL_MESSAGE_CONTENT));

                messages.add(aNewMessage);
            }

        } catch (SQLException e) {
            System.err.println(ERR_MSG_GET_MESSAGES_FROM_DB);
            e.printStackTrace();
        }

        return messages;
    }

    @Override
    public ArrayList<Message> getMessagesByGroup(Message aMessage) {
        Message aNewMessage = ModelObjectBuilder.getMessageObject();
        ArrayList<Message> messages = new ArrayList<Message>();
        ResultSet rs;
        Group aGroup;
        User aUser;

        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(PS_GET_MESAGES_BY_GROUP)) {

            statement.setInt(PARAMETER_1, aMessage.getGroup().getGroupId());
            rs = statement.executeQuery();

            while(rs.next()) {
                aGroup = ModelObjectBuilder.getGroupObject();
                aUser = ModelObjectBuilder.getUserObject();

                aNewMessage.setMessageId(rs.getLong(COL_MESSAGE_ID));
                aGroup.setGroupId(rs.getInt(COL_MESSAGE_GROUP_ID));
                aNewMessage.setGroup(aGroup);
                aUser.setUserId(rs.getInt(COL_MESSAGE_USER_ID));
                aNewMessage.setUser(aUser);
                aNewMessage.setContent(rs.getString(COL_MESSAGE_CONTENT));

                messages.add(aNewMessage);
            }

        } catch (SQLException e) {
            System.err.println(ERR_MSG_GET_MESSAGES_FROM_DB);
            e.printStackTrace();
        }

        return messages;
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
