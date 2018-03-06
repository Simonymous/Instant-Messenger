package service.exceptions;

public class UserAlreadyExistsException extends RuntimeException {
    public  UserAlreadyExistsException(String errMsg) {
        super(errMsg);
    }
}
