package model.exceptions;

public class UserDoesNotExistException extends RuntimeException {
    public UserDoesNotExistException(String errMsg) {
        super(errMsg);
    }
}
