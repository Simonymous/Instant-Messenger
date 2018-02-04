package rest.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static rest.constants.GroupRestConstants.*;

@Provider
public class GroupAlreadyExistsException extends Exception implements ExceptionMapper<GroupAlreadyExistsException> {
    private static final long serialVersionID = 2L;

    public GroupAlreadyExistsException(){
        super(ERR_GROUP_ALREADY_EXISTS);
    }

    public GroupAlreadyExistsException(String string){
        super(string);
    }

    @Override
    public Response toResponse(GroupAlreadyExistsException exception){
        return Response.status(409).entity(exception.getMessage()).type("text/plain").build();
    }
}
