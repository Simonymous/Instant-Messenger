package dao.constants;

import dao.enums.ColNameGroup_User;
import dao.enums.TableName;

public class Group_UserDaoConstants {
    public static final String TABLE_GROUP_USERS = TableName.Group_User.getTableName();
    public static final String COL_GROUP_GROUP_ID = ColNameGroup_User.GroupId.getColumnName();
    public static final String COL_USER_USER_ID = ColNameGroup_User.UserId.getColumnName();

    public static final String ERR_MSG_ADD_USER_TO_GROUP = "Error: Couldn't add User to Group!\n";
    public static final String ERR_REMOVE_USER_FROM_GROUP = "Error: Couldn't remove User from Group!\n";
    public static final String ERR_MSG_GET_GROUP_FROM_DB = "Error: Group not found!\n";
    public static final String ERR_MSG_GET_USERS_FROM_DB = "Error: Users not found!\n";
    public static final String GROUP_DOSENT_EXIST = "Error: Group dosent exist!\n";
}
