package rest.exceptions;

import static rest.constants.GroupRestConstants.ERR_USER_GROUP_DOES_NOT_EXIST;


public class UserOrGroupDoesNotExistException extends Exception {
    private static final long serialVersionID = 2L;

    public UserOrGroupDoesNotExistException() {
        super(ERR_USER_GROUP_DOES_NOT_EXIST);
    }

    public UserOrGroupDoesNotExistException(String string) {
        super(string);
    }

}

