package im.rest.exceptions;

import im.rest.constants.GroupRestConstants;

/**
 * own exception to throw if a group already exists
 */
public class GroupAlreadyExistsException extends Exception {
    private static final long serialVersionID = 2L;

    /**
     * default constructor
     */
    public GroupAlreadyExistsException() {
        super(GroupRestConstants.ERR_GROUP_ALREADY_EXISTS);
    }

    /**
     * constructor with given error message
     * @param string
     */
    public GroupAlreadyExistsException(String string) {
        super(string);
    }

}
