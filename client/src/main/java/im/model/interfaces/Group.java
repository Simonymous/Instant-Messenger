package im.model.interfaces;

import java.util.ArrayList;

public interface Group {
    int getGroupId();
    void setGroupId(int groupId);
    String getGroupName();
    void setGroupName(String groupName);

    void addUser(User aUser);
    void removeUser(User aUser);
    ArrayList<User> getUsers();
}
