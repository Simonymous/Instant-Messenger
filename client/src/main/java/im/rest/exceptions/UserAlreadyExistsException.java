package im.rest.exceptions;

import im.rest.constants.UserRestConstants;

/**
 * own exception to throw if a user does already exist
 */
public class UserAlreadyExistsException extends Exception {
    private static final long serialVersionID = 4L;

    /**
     * default constructor
     */
    public UserAlreadyExistsException() {
        super(UserRestConstants.ERR_USER_ALREADY_EXISTS);
    }

    /**
     * constructor with given error message
     *
     * @param string
     */
    public UserAlreadyExistsException(String string) {
        super(string);
    }

}
