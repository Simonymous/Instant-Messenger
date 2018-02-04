package rest.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static rest.constants.UserRestConstants.*;

@Provider
public class UserDoesNotExistException extends Exception implements ExceptionMapper<UserDoesNotExistException> {
    private static final long serialVersionID = 1L;

    public UserDoesNotExistException(){
        super(ERR_USER_DOES_NOT_EXIST);
    }

    public UserDoesNotExistException(String string){
        super(string);
    }

    @Override
    public Response toResponse(UserDoesNotExistException exception){
        return Response.status(404).entity(exception.getMessage()).type("text/plain").build();
    }
}
