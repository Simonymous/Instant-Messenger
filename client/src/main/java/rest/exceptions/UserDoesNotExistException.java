package rest.exceptions;

import static rest.constants.UserRestConstants.ERR_USER_DOES_NOT_EXIST;


public class UserDoesNotExistException extends Exception {
    private static final long serialVersionID = 1L;

    public UserDoesNotExistException() {
        super(ERR_USER_DOES_NOT_EXIST);
    }

    public UserDoesNotExistException(String string) {
        super(string);
    }

}
