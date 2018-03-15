package dao.enums;

public enum TableName {
    User("User"), ChatGroup("chatGroup"), Group_User("Group_User"), Message("Message");

    private String tableName;

    TableName(String tableName) {
        this.tableName = tableName;
    }

    public String getTableName() {
        return tableName;
    }
}
