package rest.Exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static rest.constants.UserRestConstants.*;

@Provider
public class UserAlreadyExistsException extends Exception implements ExceptionMapper<UserAlreadyExistsException> {
    private static final long serialVersionID = 4L;

    public UserAlreadyExistsException(){
        super(ERR_USER_ALREADY_EXISTS);
    }

    public UserAlreadyExistsException(String string){
        super(string);
    }

    @Override
    public Response toResponse(UserAlreadyExistsException exception){
        return Response.status(409).entity(exception.getMessage()).type("text/plain").build();
    }
}
