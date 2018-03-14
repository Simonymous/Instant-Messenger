package im.core;

import com.google.common.hash.Hashing;
import model.classes.UserImpl;
import model.interfaces.User;
import rest.services.UserRestClientImpl;

import java.nio.charset.StandardCharsets;

public class UserAuthenticator {
    private UserRestClientImpl urci;

    /**
     * Creates a new UserAuthenticator object
     */
    public UserAuthenticator() {
        urci = new UserRestClientImpl();
    }

    /**
     * Checks if a user, given by name, exsits on the Server
     *
     * @param name name of user
     * @return does user exist?
     */
    public boolean doesUserExist(String name) {
        User user = urci.getTheUserByName(name);

        return user != null;
    }

    /**
     * Adds a new User to the Server
     *
     * @param name     name of new user
     * @param password password of new user
     */
    public void addNewUser(String name, String password) {
        User newUser = urci.addUser(name, password);
    }

    /**
     * Checks if a given clear password is valid
     *
     * @param password non hasehd password
     * @return password matches?
     */
    public boolean isPasswordOk(String password) {
        String hashedPassword = Hashing.sha256().hashString(password, StandardCharsets.UTF_8).toString();
        return OwnUser.getInstance().getPassword().equals(hashedPassword);
    }

    /**
     * Changes the Username of the authenticated User
     *
     * @param name new Username
     */
    public void changeUsername(String name) {
        OwnUser ownUser = OwnUser.getInstance();
        ownUser.setUsername(name);
        User user = new UserImpl();
        user.setUsername(ownUser.getUsername());
        user.setPassword(ownUser.getPassword());
        user.setUserId(ownUser.getUserId());
        urci.updateUser(Integer.toString(ownUser.getUserId()), user);
    }

    /**
     * Changes the Password of the authenticated User
     *
     * @param password new unhashed Password
     */
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

    /**
     * Authenticates a user
     *
     * @param name     name of user
     * @param password non hashed password of user
     * @return does name/password match?
     */
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
