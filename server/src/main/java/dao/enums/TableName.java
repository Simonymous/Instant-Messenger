package dao.enums;

public enum TableName {
    User("user"), ChatGroup("chatGroup"), Group_User("group_User"), Message("message");

    private String tableName;

    TableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
