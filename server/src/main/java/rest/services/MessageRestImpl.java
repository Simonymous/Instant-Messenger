package rest.services;

import builder.ServiceObjectBuilder;
import com.google.gson.Gson;
import rest.exceptions.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static rest.constants.GeneralRestConstants.*;
import static rest.constants.GroupRestConstants.*;
import static rest.constants.MessageRestConstants.*;
import static rest.constants.UserRestConstants.*;

@Path(MessageRestImpl.webContextPath)
public class MessageRestImpl implements rest.interfaces.MessageRest {
    static final String webContextPath = "/im";
    private static final Gson gSon = new Gson();
    private Response.Status status;

    @Override
    @POST
    @Path("addMessage/{groupId}/{userId}/{content}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMessage(@PathParam("groupId") final int groupId, @PathParam("userId") final int userId, @PathParam("content") final String content){
        Response response = null;

        try{
            ServiceObjectBuilder.getMessageServiceObject().addMessage(groupId, userId, content);
            response = Response.status(200, MESSAGE_ADDED).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @Override
    @DELETE
    @Path("removeMessage/{messageId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeMessage(@PathParam("messageId") final long messageId) throws MessageDoesNotExistException{
        Response response = null;

        try{
            ServiceObjectBuilder.getMessageServiceObject().removeMessage(messageId);
            response = Response.status(200, MESSAGE_REMOVED).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (MessageDoesNotExistException e) {
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_MESSAGE_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @Override
    @GET
    @Path("getMessageById")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessageById(@QueryParam("messageId") final long messageId){
        Response response = null;

        try{
            String json = gSon.toJson(ServiceObjectBuilder.getMessageServiceObject().getMessageById(messageId));
            response = Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        catch (MessageDoesNotExistException e) {
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_MESSAGE_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @Override
    @GET
    @Path("getMessageContentById")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessageContentById(@QueryParam("messageId") final long messageId){
        Response response = null;

        try{
            String json = gSon.toJson(ServiceObjectBuilder.getMessageServiceObject().getMessageContentById(messageId));
            response = Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        catch (MessageDoesNotExistException e) {
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_MESSAGE_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @Override
    @GET
    @Path("getMessagesForUser")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessagesForUser(@QueryParam("userId") final int userId){
        Response response = null;

        try{
            String json = gSon.toJson(ServiceObjectBuilder.getMessageServiceObject().getMessagesForUser(userId));
            response = Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        catch (UserDoesNotExistException e) {
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_USER_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @Override
    @GET
    @Path("getMessagesForGroup")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessagesForGroup(@QueryParam("groupId") final int groupId){
        Response response = null;

        try{
            String json = gSon.toJson(ServiceObjectBuilder.getMessageServiceObject().getMessagesForGroup(groupId));
            response = Response.ok(json, MediaType.APPLICATION_JSON).build();
        }
        catch (GroupDoesNotExistException e) {
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }
}
