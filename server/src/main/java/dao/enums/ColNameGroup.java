package dao.enums;

public enum ColNameGroup {
    GroupId("groupId"), GroupName("groupname");

    private String columnName;

    ColNameGroup(String columName) {
        this.columnName = columName;
    }

    public String getColumnName() {
        return columnName;
    }
}
