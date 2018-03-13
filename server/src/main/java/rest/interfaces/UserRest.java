package rest.interfaces;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.classes.UserImpl;
import model.interfaces.User;

/*
*   UserRest Interface
* */
public interface UserRest {

    /*
    *   Returns User for given Name
    *   @return User capsuled in Response
     */
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserByName(@QueryParam("byname") String qu);

    /*
    *   Returns User for given Name
    *   @return User capsuled in Response
     */
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    Response getTheUserByName(@QueryParam("byname") String qu);

    /*
     *   Returns User for given Id
     *   @return User capsuled in Response
     */
    @GET
    @Path("users/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserById(String username);

    /*
     *   Adds new User
     *   @return Http-Statuscode capsuled in Response
     */
    @POST
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response addUser(String user);

    /*
     *   Updates an existing User
     *   @return Http-Statuscode capsuled in Response
     */
    @POST
    @Path("users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateUser(@PathParam("id") String id, String user);

    /*
     *   Removes an existing User
     *   @return Http-Statuscode capsuled in Response
     */
    @DELETE
    @Path("users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response removeUser(@PathParam("id") String id);

    @GET
    @Path("users/{userId}/groups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsOfUser(@PathParam("userId") final String userId);
}
