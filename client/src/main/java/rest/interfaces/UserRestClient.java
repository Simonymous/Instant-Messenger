package rest.interfaces;

import model.interfaces.User;
import model.interfaces.UserQueryResponse;


public interface UserRestClient {

    void updateUser(final String id, User user);

    UserQueryResponse getUserByName(final String name);

    User addUser(final String name, final String passwd);

    void removeUser(final String userName);

    User getUserById(final String id);

    User getTheUserByName(final String name);

}
