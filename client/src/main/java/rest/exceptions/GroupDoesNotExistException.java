package rest.exceptions;

import static rest.constants.GroupRestConstants.ERR_GROUP_DOES_NOT_EXIST;


public class GroupDoesNotExistException extends Exception {
    private static final long serialVersionID = 2L;

    public GroupDoesNotExistException() {
        super(ERR_GROUP_DOES_NOT_EXIST);
    }

    public GroupDoesNotExistException(String string) {
        super(string);
    }
}
