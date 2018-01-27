package dao.constants;

import dao.enums.ColNameUser;
import dao.enums.TableName;

public class AuthorDaoConstants {
    public static final String TABLE_USER = TableName.User.getTableName();
    public static final String COL_USER_USERNAME = ColNameUser.Username.getColumnName();
    public static final String COL_USER_PASSWORD = ColNameUser.Password.getColumnName();
    public static final String COL_USER_ID = ColNameUser.ID.getColumnName();
    public static final String COL_USER_ACTIVE = ColNameUser.Active.getColumnName();

    public static final String ERR_MSG_ADD_NEW_USER =     "Error: New User can not be inserted!\n";
    public static final String ERR_MSG_REMOVE_USER =      "Error: User can not be removed!\n";
    public static final String ERR_MSG_CHANGE_ATTRIBUT =    "Error: An atribute of the table User coudlnt be changed!\n";
    public static final String ERR_MSG_GET_USER_FROM_DB = "Error: Coudnt read all Users from DB!\n";
}
