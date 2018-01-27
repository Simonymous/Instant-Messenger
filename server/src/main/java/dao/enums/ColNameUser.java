package dao.enums;

public enum ColNameUser {
    Username("username"), Password("password"), Active("active"), ID("userId");

    private String columnName;

    ColNameUser(String columName) {
        this.columnName = columName;
    }

    public String getColumnName() {
        return columnName;
    }
}
