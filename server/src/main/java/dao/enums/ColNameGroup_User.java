package dao.enums;

public enum ColNameGroup_User {
    GroupId("groupGroupId"), UserId("userUserId");

    private String columnName;

    ColNameGroup_User(String columName) {
        this.columnName = columName;
    }

    public String getColumnName() {
        return columnName;
    }
}
