package rest.exceptions;

import static rest.constants.GroupRestConstants.ERR_USER_GROUP_DOES_NOT_EXIST;

/**
 * own exception to throw if a group or user does not exist
 */
public class UserOrGroupDoesNotExistException extends Exception {
    private static final long serialVersionID = 2L;

    /**
     * default constructor
     */
    public UserOrGroupDoesNotExistException() {
        super(ERR_USER_GROUP_DOES_NOT_EXIST);
    }

    /**
     * constructor with given error message
     * @param string
     */
    public UserOrGroupDoesNotExistException(String string) {
        super(string);
    }

}

