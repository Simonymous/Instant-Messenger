package rest.Exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static rest.constants.GroupRestConstants.ERR_GROUP_ALREADY_EXISTS;

@Provider
public class GroupDoesNotExistException extends Exception implements ExceptionMapper<GroupDoesNotExistException>{
    private static final long serialVersionID = 2L;

    public GroupDoesNotExistException(){
        super(ERR_GROUP_ALREADY_EXISTS);
    }

    public GroupDoesNotExistException(String string){
        super(string);
    }

    @Override
    public Response toResponse(GroupDoesNotExistException exception){
        return Response.status(404).entity(exception.getMessage()).type("text/plain").build();
    }
}
