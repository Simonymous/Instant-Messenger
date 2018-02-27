package rest.interfaces;

import model.interfaces.Group;
import java.util.ArrayList;


public interface GroupRestClient {

    Group addGroup(Group gro);

    void removeGroup(final String groupId);

    ArrayList<Integer> getUsersOfGroup(final String groupId);

    void addUserToGroup(final String groupId, final String userId);

    void removeUserFromGroup(final String groupId, final String userId);

    Group getGroupById(final String groupId);

    void changeGroup(final String groupId, Group group);

    void postMessage(final String groupId, final String jsonMessage);

}
