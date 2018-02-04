package rest.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static rest.constants.GroupRestConstants.*;

@Provider
public class GroupDoesNotExistException extends Exception implements ExceptionMapper<GroupDoesNotExistException>{
    private static final long serialVersionID = 2L;

    public GroupDoesNotExistException(){
        super(ERR_GROUP_DOES_NOT_EXIST);
    }

    public GroupDoesNotExistException(String string){
        super(string);
    }

    @Override
    public Response toResponse(GroupDoesNotExistException exception){
        return Response.status(409).entity(exception.getMessage()).type("text/plain").build();
    }
}
