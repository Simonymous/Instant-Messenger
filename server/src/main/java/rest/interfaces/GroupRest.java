package rest.interfaces;

import model.interfaces.Group;
import model.interfaces.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public interface GroupRest {

    @GET
    @Path("groups")
    @Produces(MediaType.APPLICATION_JSON)
    Response getGroupsByFilter(@QueryParam("byuser") List<String> users, @QueryParam("byname") String name);

    @POST
    @Path("groups")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response addGroup(Group group);

    @DELETE
    @Path("groups/{groupId}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response removeGroup(@PathParam("groupId") String groupId);

    @GET
    @Path("groups/{groupId}/users")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUsersOfGroup(@PathParam("groupId") String groupId);

    @POST
    @Path("groups/{groupId}/users/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response addUserToGroup(@PathParam("groupId") String groupId, @PathParam("userId") String userId);

    @DELETE
    @Path("groups/{groupId}/users/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response removeUserFromGroup(@PathParam("groupId") String groupId, @PathParam("userId") String userId);

    @GET
    @Path("groups/{groupId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response getGroupById(@PathParam("groupId") String groupId);

    @POST
    @Path("groups/{groupId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response changeGroup(@PathParam("groupId") String groupId, Group group);

     @GET
     @Path("groups/{groupId}/messages")
     @Produces(MediaType.APPLICATION_JSON)
     Response getMessagesOfGroup(@PathParam("groupId") String groupId, @DefaultValue("0") @QueryParam("page") String page);

    @POST
    @Path("groups/{groupId}/messages")
    @Consumes(MediaType.APPLICATION_JSON)
    Response postMessage(@PathParam("groupId") String groupId, String message);

}
