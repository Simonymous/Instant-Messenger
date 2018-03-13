package im.core;

import com.google.common.hash.Hashing;
import model.classes.UserImpl;
import model.interfaces.User;
import model.interfaces.UserQueryResponse;
import rest.services.GroupRestClientImpl;
import rest.services.UserRestClientImpl;

import java.nio.charset.StandardCharsets;

public class UserAuthenticator {
    private UserRestClientImpl urci;

    public UserAuthenticator() {
        urci = new UserRestClientImpl();
    }

    public boolean doesUserExist(String name) {
        User user = urci.getTheUserByName(name);

        return user != null;
    }

    public void addNewUser(String name, String password) {
        User newUser = urci.addUser(name, password);
    }

    public boolean isPasswordOk(String password) {
        String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        return OwnUser.getInstance().getPassword().equals(hashedPassword);
    }

    public void changeUsername(String name) {
        OwnUser ownUser = OwnUser.getInstance();
        ownUser.setUsername(name);
        User user = new UserImpl();
        user.setUsername(ownUser.getUsername());
        user.setPassword(ownUser.getPassword());
        user.setUserId(ownUser.getUserId());
        urci.updateUser(Integer.toString(ownUser.getUserId()), user);
    }

    public void changPassword(String password) {
        String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        OwnUser ownUser = OwnUser.getInstance();
        ownUser.setPassword(hashedPassword);
        User user = new UserImpl();
        user.setUsername(ownUser.getUsername());
        user.setPassword(ownUser.getPassword());
        user.setUserId(ownUser.getUserId());
        urci.updateUser(Integer.toString(ownUser.getUserId()), user);
    }

    public Boolean authenticateUser(String name, String password) {
        User user = urci.getTheUserByName(name);
        if (user == null) {
            return false;
        } else {
            String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
            if (user.getPassword().equals(hashedPassword)) {
                OwnUser.getInstance().createUser(user.getUserId(), user.getUsername(), user.getPassword());
                return true;
            } else return false;
        }
    }
}
