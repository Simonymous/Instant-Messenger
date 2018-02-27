package rest.exceptions;

import static rest.constants.GroupRestConstants.ERR_GROUP_ALREADY_EXISTS;


public class GroupAlreadyExistsException extends Exception {
    private static final long serialVersionID = 2L;

    public GroupAlreadyExistsException() {
        super(ERR_GROUP_ALREADY_EXISTS);
    }

    public GroupAlreadyExistsException(String string) {
        super(string);
    }

}
