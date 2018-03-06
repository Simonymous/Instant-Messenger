package dao.constants;

import dao.enums.ColNameMessage;
import dao.enums.TableName;

public class MessageDaoConstants {
    public static final String TABLE_MESSAGE = TableName.Message.getTableName();
    public static final String COL_MESSAGE_ID = ColNameMessage.MessageId.getColumnName();
    public static final String COL_MESSAGE_GROUP_ID = ColNameMessage.GroupId.getColumnName();
    public static final String COL_MESSAGE_USER_ID = ColNameMessage.UserId.getColumnName();
    public static final String COL_MESSAGE_CONTENT = ColNameMessage.Content.getColumnName();

    public static final String ERR_MSG_ADD_NEW_MESSAGE =     "Error: New Message can not be inserted!\n";
    public static final String ERR_MSG_REMOVE_MESSAGE =      "Error: Message can not be removed!\n";
    public static final String ERR_MSG_CHANGE_ATTRIBUT =    "Error: An attribute of the table User couldn't be changed!\n";
    public static final String ERR_MSG_GET_MESSAGES_FROM_DB = "Error: Couldn't read all Messages from DB!\n";
    public static final String ERR_MSG_GET_MESSAGE_FROM_DB = "Error: Couldn't read Message from DB!\n";
}
