package rest.interfaces;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import model.classes.UserImpl;
import model.interfaces.User;

public interface UserRest {

    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserByName(@QueryParam("byname") String qu); // TODO create UserQuery class

    @GET
    @Path("users/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getUserById(String username);

    @POST
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response addUser(String user);

    @POST
    @Path("users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateUser(@PathParam("id") String id, String user);

    @DELETE
    @Path("users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Response removeUser(@PathParam("id") String id);

}
