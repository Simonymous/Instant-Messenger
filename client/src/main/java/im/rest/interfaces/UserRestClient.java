package im.rest.interfaces;

import im.model.interfaces.User;
import im.model.interfaces.UserQueryResponse;

import java.util.ArrayList;

/**
 * interface for user implementation of rest client interface
 */
public interface UserRestClient {

    void updateUser(final String id, User user);

    UserQueryResponse getUserByName(final String name);

    UserQueryResponse getAllUser();

    User addUser(final String name, final String passwd);

    void removeUser(final String userName);

    User getUserById(final String id);

    User getTheUserByName(final String name);

    ArrayList<Integer> getGroupsOfUser(final String userId);

    boolean authenticateUser(final String userId, final String passwd);

}
