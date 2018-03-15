package rest.exceptions;

import static rest.constants.UserRestConstants.ERR_USER_DOES_NOT_EXIST;

/**
 * own exception to throw if a user does not exist
 */
public class UserDoesNotExistException extends Exception {
    private static final long serialVersionID = 1L;

    /**
     * default constructor
     */
    public UserDoesNotExistException() {
        super(ERR_USER_DOES_NOT_EXIST);
    }
    
    /**
     * constructor with given error message
     */
    public UserDoesNotExistException(String string) {
        super(string);
    }

}
