package service.interfaces;

import model.interfaces.Group;

public interface GroupService {
    void addNewGroup(String name);
    void removeGroup(int id);
    void changeGroupName(int id, String newName);
    Group getGroupById(int id);
}
