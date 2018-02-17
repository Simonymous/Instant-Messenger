package rest.interfaces;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public interface UserRest {
    @GET
    @Path("doesUserExist")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response doesUserExist(@QueryParam("name") String name);

    @POST
    @Path("changeUserName/{newName}/{oldName}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response changeUserName(@PathParam("newName") String newName, @PathParam("oldName") String oldName);

    @POST
    @Path("changeUserPassword/{name}/{password}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response changeUserPassword(@PathParam("name") String userName, @PathParam("password") String password);

    @GET
    @Path("validCredentials")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response validCredentials(@QueryParam("name") String userName, @QueryParam("password") String password);

    @GET
    @Path("getUserId")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserId(@QueryParam("name") String userName);

    @POST
    @Path("addUser/{name}/{password}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response addUser(@PathParam("name") String userName, @PathParam("password") String password);

    @DELETE
    @Path("removeUser/{name}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response removeUser(@PathParam("name") String userName);

    @GET
    @Path("getStatusForUser")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response getStatusForUser(@QueryParam("name") String userName);

    @POST
    @Path("setStatusForUser/{name}/{status}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response setStatusForUser(@PathParam("name") String userName, @PathParam("status") Boolean userStatus);

    @GET
    @Path("getGroupsForUser")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    Response getGroupsForUser(@QueryParam("name") String userName);
}
