package im.model.exceptions;

/**
 * own UserDoesNotExistException to throw if user does not exist
 */
public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String errMsg) {
        super(errMsg);
    }
}
