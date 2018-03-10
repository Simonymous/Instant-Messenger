package im.core;

import model.interfaces.User;
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
            return (urci.getUserByName(name) != null); //TODO: getUserByName muss entsprechend angepasst werden
        }

        public void addNewUser(String name, String password) {
            User newUser = urci.addUser(name, password); //TODO: Übergabeparameter anpassen
        }

        public boolean authenticateUser(String name, String password) {
            return urci.authenticateUser(name, password);
        }
}
