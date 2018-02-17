package rest.interfaces;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface GroupRest {
    @POST
    @Path("addNewGroup/{name}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response addNewGroup(@PathParam("name") String groupName);

    @DELETE
    @Path("removeGroup/{groupId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response removeGroup(@PathParam("groupId") int groupId);

    @POST
    @Path("addUserToGroup/{groupId}/{name}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response addUserToGroup(@PathParam("groupId") int groupId, @PathParam("name") String userName);

    @DELETE
    @Path("removeUserFromGroup/{userId}/{groupId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response removeUserFromGroup(@PathParam("userId") int userId, @PathParam("groupId") int groupId);

    @GET
    @Path("getGroupById")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response getGroupById(@QueryParam("groupId") int groupId);

    @POST
    @Path("changeGroupName/{groupId}/{name}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response changeGroupName(@PathParam("groupId") int groupId, @PathParam("name") String newName);

    @GET
    @Path("getUsersForGroup")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUsersForGroup(@QueryParam("groupId") int groupId);
}
