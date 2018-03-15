package rest.exceptions;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import static rest.constants.MessageRestConstants.ERR_MESSAGE_DOES_NOT_EXIST;

/**
 * own server exception to throw if a message does not exist
 */
@Provider
public class MessageDoesNotExistException extends Exception implements ExceptionMapper<MessageDoesNotExistException> {
    private static final long serialVersionID = 2L;

    /**
     * default constructor
     */
    public MessageDoesNotExistException() {
        super(ERR_MESSAGE_DOES_NOT_EXIST);
    }

    /**
     * custructor with given error message
     * @param string
     */
    public MessageDoesNotExistException(String string) {
        super(string);
    }
    
    /**
     * method to convert exception to response with status 404
     */
    @Override
    public Response toResponse(MessageDoesNotExistException exception) {
        return Response.status(404).entity(exception.getMessage()).type("text/plain").build();
    }
}
