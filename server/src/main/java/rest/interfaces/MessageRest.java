package rest.interfaces;

import rest.exceptions.MessageDoesNotExistException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface MessageRest {
    @POST
    @Path("addMessage/{groupId}/{userId}/{content}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response addMessage(@PathParam("groupId") int groupId, @PathParam("userId") int userId, @PathParam("content") String content);

    @DELETE
    @Path("removeMessage/{messageId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response removeMessage(@PathParam("messageId") long messageId) throws MessageDoesNotExistException;

    @GET
    @Path("getMessageById")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response getMessageById(@QueryParam("messageId") long messageId);

    @GET
    @Path("getMessageContentById")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response getMessageContentById(@QueryParam("messageId") long messageId);

    @GET
    @Path("getMessagesForUser")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response getMessagesForUser(@QueryParam("userId") int userId);

    @GET
    @Path("getMessagesForGroup")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response getMessagesForGroup(@QueryParam("groupId") int groupId);
}
