package dao.constants;

import dao.enums.ColNameGroup;
import dao.enums.TableName;

public class GroupDaoConstants {
    public static final String TABLE_GROUP = TableName.ChatGroup.getTableName();
    public static final String COL_GROUP_ID = ColNameGroup.GroupId.getColumnName();
    public static final String COL_GROUP_NAME = ColNameGroup.GroupName.getColumnName();

    public static final String ERR_MSG_ADD_NEW_GROUP =     "Error: New Group can not be created!\n";
    public static final String ERR_MSG_REMOVE_GROUP =      "Error: Group can not be removed!\n";
    public static final String ERR_MSG_CHANGE_ATTRIBUT =    "Error: An atribute of the table User coudlnt be changed!\n";
    public static final String ERR_MSG_GET_GROUPS_FROM_DB = "Error: Coudnt read all Groups from DB!\n";
}
