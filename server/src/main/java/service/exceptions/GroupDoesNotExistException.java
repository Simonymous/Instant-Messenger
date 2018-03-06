package service.exceptions;

public class GroupDoesNotExistException extends RuntimeException{
    public  GroupDoesNotExistException(String errMsg) {
        super(errMsg);
    }
}
