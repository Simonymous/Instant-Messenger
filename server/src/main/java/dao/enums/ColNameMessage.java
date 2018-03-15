package dao.enums;

public enum ColNameMessage {
    MessageId("messageId"), GroupId("groupGroupId"), UserId("userUserId"), Content("content");

    private String columnName;

    ColNameMessage(String columName) {
        this.columnName = columName;
    }

    public String getColumnName() {
        return columnName;
    }
}
