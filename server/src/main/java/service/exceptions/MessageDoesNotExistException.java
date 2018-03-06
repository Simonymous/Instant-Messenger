package service.exceptions;

public class MessageDoesNotExistException extends RuntimeException{
    public  MessageDoesNotExistException(String errMsg) {
        super(errMsg);
    }
}
