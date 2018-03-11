package im.core;

import model.interfaces.User;
import model.interfaces.UserQueryResponse;
import rest.services.GroupRestClientImpl;
import rest.services.UserRestClientImpl;

public class UserAuthenticator {
        private UserRestClientImpl urci;
        private GroupRestClientImpl grci;

        public UserAuthenticator() {
            urci = new UserRestClientImpl();
            grci = new GroupRestClientImpl();
        }

        public boolean doesUserExist(String name) {
            UserQueryResponse user = urci.getUserByName(name);
            return !user.getIds().isEmpty();
        }

        public void addNewUser(String name, String password) {
            User newUser = urci.addUser(name, password);
        }

        public boolean authenticateUser(String name, String password) {
            UserQueryResponse user = urci.getUserByName(name);
            if (user.getIds().isEmpty()) {
                return false;
            } else {
                String id = user.getIds().get(0); //????
                return urci.authenticateUser(id, password);
            }
        }
}
