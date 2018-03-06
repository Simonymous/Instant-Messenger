package rest.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static rest.constants.MessageRestConstants.ERR_MESSAGE_DOES_NOT_EXIST;

@Provider
public class MessageDoesNotExistException extends Exception implements ExceptionMapper<MessageDoesNotExistException>{
    private static final long serialVersionID = 2L;

    public MessageDoesNotExistException(){
        super(ERR_MESSAGE_DOES_NOT_EXIST);
    }

    public MessageDoesNotExistException(String string){
        super(string);
    }

    @Override
    public Response toResponse(MessageDoesNotExistException exception){
        return Response.status(404).entity(exception.getMessage()).type("text/plain").build();
    }
}
