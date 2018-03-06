package rest.interfaces;

import model.interfaces.Group;
import model.interfaces.Message;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

/*
 *   GroupRest Interface
 * */
public interface GroupRest {

    /*
     *   Returns Group for given Filter
     *   @return Group capsuled in Response
     */
    @GET
    @Path("groups")
    @Produces(MediaType.APPLICATION_JSON)
    Response getGroupsByFilter(@QueryParam("byuser") List<String> users, @QueryParam("byname") String name);

    /*
     *   Adds new Group
     *   @return Http-Statuscode capsuled in Response
     */
    @POST
    @Path("groups")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response addGroup(String group);

    /*
     *   Removes existing Group
     *   @return Http-Statuscode capsuled in Response
     */
    @DELETE
    @Path("groups/{groupId}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response removeGroup(@PathParam("groupId") String groupId);

    /*
     *   Returns Users for Group
     *   @return User-List capsuled in Response
     */
    @GET
    @Path("groups/{groupId}/users")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUsersOfGroup(@PathParam("groupId") String groupId);

    /*
     *   Adds User to Group
     *   @return Http-Statuscode capsuled in Response
     */
    @POST
    @Path("groups/{groupId}/users/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response addUserToGroup(@PathParam("groupId") String groupId, @PathParam("userId") String userId);

    /*
     *   Removes User from Group
     *   @return Http-Statuscode capsuled in Response
     */
    @DELETE
    @Path("groups/{groupId}/users/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response removeUserFromGroup(@PathParam("groupId") String groupId, @PathParam("userId") String userId);

    /*
     *   Returns Group by given Id
     *   @return Group capsuled in Response
     */
    @GET
    @Path("groups/{groupId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response getGroupById(@PathParam("groupId") String groupId);

    /*
     *   Changes existing Group
     *   @return Http-Statuscode capsuled in Response
     */
    @POST
    @Path("groups/{groupId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Response changeGroup(@PathParam("groupId") String groupId, String group);

    /*
     *   Returns Message for given Group
     *   @return Message-List capsuled in Response
     */
    @GET
    @Path("groups/{groupId}/messages")
    @Produces(MediaType.APPLICATION_JSON)
    Response getMessagesOfGroup(@PathParam("groupId") String groupId, @DefaultValue("0") @QueryParam("page") String page);

    /*
     *   Adds new Message to Group
     *   @return Http-Statuscode capsuled in Response
     */
    @POST
    @Path("groups/{groupId}/messages")
    @Consumes(MediaType.APPLICATION_JSON)
    Response postMessage(@PathParam("groupId") String groupId, String message);

}
