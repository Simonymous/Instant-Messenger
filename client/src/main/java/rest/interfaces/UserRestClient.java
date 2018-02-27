package rest.interfaces;

import model.interfaces.User;
import model.interfaces.UserQueryResponse;


public interface UserRestClient {

    void updateUser(final String id, final String json);

    UserQueryResponse getUserByName(final String name);

    User addUser(String json);

    void removeUser(final String userName);

    User getUserById(final String id);

}
