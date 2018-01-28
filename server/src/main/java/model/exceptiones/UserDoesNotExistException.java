package model.exceptiones;

public class UserDoesNotExistException extends RuntimeException{
    public UserDoesNotExistException(String errMsg){
        super(errMsg);
    }
}
