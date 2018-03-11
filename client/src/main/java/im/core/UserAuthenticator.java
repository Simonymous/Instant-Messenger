package im.core;

import com.google.common.hash.Hashing;
import model.interfaces.User;
import model.interfaces.UserQueryResponse;
import rest.services.GroupRestClientImpl;
import rest.services.UserRestClientImpl;

import java.nio.charset.StandardCharsets;

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

        public Boolean authenticateUser(String name, String password) {
            UserQueryResponse user = urci.getUserByName(name);
            if (user.getIds().isEmpty()) {
                return null;
            } else {
                String id = user.getIds().get(0); //????
                User u = urci.getUserById(id);
                String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
                if (u.getPassword().equals(hashedPassword)) {
                    OwnUser.getInstance().createUser(u.getUserId(), u.getUsername(), u.getPassword());
                    return true;
                } else return false;
            }
        }
}
