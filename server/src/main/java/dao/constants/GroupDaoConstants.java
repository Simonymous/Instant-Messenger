package dao.constants;

import dao.enums.ColNameGroup;
import dao.enums.TableName;

public class GroupDaoConstants {
    public static final String TABLE_GROUP = TableName.ChatGroup.getTableName();
    public static final String COL_GROUP_ID = ColNameGroup.GroupId.getColumnName();
    public static final String COL_GROUP_NAME = ColNameGroup.GroupName.getColumnName();

    public static final String PS_CHANGE_ATTRIBUT = "Error: Couldn't change attribute!\n";
    public static final String ERR_MSG_ADD_NEW_GROUP =     "Error: New Group can not be created!\n";
    public static final String ERR_MSG_REMOVE_GROUP =      "Error: Group can not be removed!\n";
    public static final String ERR_MSG_CHANGE_ATTRIBUT =    "Error: An attribute of the table User couldn't be changed!\n";
    public static final String ERR_MSG_GET_GROUPS_FROM_DB = "Error: Couldn't read all Groups from DB!\n";
    public static final String ERR_MSG_ADD_USER_TO_GROUP = "Error: Couldn't add User to Group!\n";
    public static final String ERR_REMOVE_USER_FROM_GROUP = "Error: Couldn't remove User from Group!\n";
    public static final String ERR_MSG_GET_GROUP_FROM_DB = "Error: Group not found!\n";
}
