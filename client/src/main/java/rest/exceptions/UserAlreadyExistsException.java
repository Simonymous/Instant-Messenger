package rest.exceptions;

import static rest.constants.UserRestConstants.ERR_USER_ALREADY_EXISTS;


public class UserAlreadyExistsException extends Exception {
    private static final long serialVersionID = 4L;

    public UserAlreadyExistsException() {
        super(ERR_USER_ALREADY_EXISTS);
    }

    public UserAlreadyExistsException(String string) {
        super(string);
    }

}
