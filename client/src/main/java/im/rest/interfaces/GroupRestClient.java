package im.rest.interfaces;

import im.model.interfaces.Group;
import im.model.interfaces.Message;

import java.util.ArrayList;

/**
 * interface for group implementation of rest client interface
 */
public interface GroupRestClient {

    Group addGroup(Group gro);

    void removeGroup(final String groupId);

    ArrayList<Integer> getUsersOfGroup(final String groupId);

    void addUserToGroup(final String groupId, final String userId);

    void removeUserFromGroup(final String groupId, final String userId);

    Group getGroupById(final String groupId);

    void changeGroup(final String groupId, Group group);

    void postMessage(final String groupId, Message msg);

}
