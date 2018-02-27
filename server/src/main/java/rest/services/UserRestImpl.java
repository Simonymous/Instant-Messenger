package rest.services;

import builder.ServiceObjectBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.classes.UserImpl;
import model.classes.UserQueryResponseImpl;
import model.interfaces.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static rest.constants.GeneralRestConstants.*;
import static rest.constants.UserRestConstants.*;


@Path(UserRestImpl.webContextPath)
public class UserRestImpl implements rest.interfaces.UserRest {
    static final String webContextPath = "/im";
    private static final Gson gSon = new Gson();
    private Response.Status status;

    private User makeUserFromJSON(String json) {
        GsonBuilder b = new GsonBuilder();
        Gson gson = b.create();
        return gson.fromJson(json, UserImpl.class);
    }

    @Override
    @POST
    @Path("users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") final String id, String json) {
        Response response = null;
        // TODO auth + response
        User user = makeUserFromJSON(json);
        try {
            ServiceObjectBuilder.getUserServiceObject().changeUserName(user.getUsername(), id); // TODO changeUser? - refactor
            response = Response.status(200, USER_NAME_CHANGED).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch(rest.exceptions.UserAlreadyExistsException e){
            System.err.println(e.getMessage());
            response = Response.status(409, ERR_USER_ALREADY_EXISTS).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch(rest.exceptions.UserDoesNotExistException e){
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
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByName(@QueryParam("byname") String qustr) {
        Response response = null;
        // TODO auth + response
        if(qustr == null) {
            return Response.status(500).build();
        }
        try {
            UserQueryResponseImpl resp = ServiceObjectBuilder.getUserServiceObject().getUsersByQuery(qustr); // TODO implement classes
            response = Response.ok(gSon.toJson(resp)).build();
        }
        catch(rest.exceptions.UserDoesNotExistException e){
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
    @POST
    @Path("users")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(String json) {
        Response response = null;
        // TODO auth + response

        User user = makeUserFromJSON(json);
        try {
            // TODO input validation
            User u = ServiceObjectBuilder.getUserServiceObject().addUser(user.getUsername(), user.getPassword()); // TODO addUser should return User (right now w/ pw)
            response = Response.ok(gSon.toJson(u)).build();
        }
        catch(rest.exceptions.UserAlreadyExistsException e){
            e.printStackTrace();
            response = Response.status(409, ERR_USER_ALREADY_EXISTS).type(MediaType.TEXT_HTML_TYPE).build(); // TODO string artifacts?
        }
        catch (Exception e) {
            e.printStackTrace();
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @Override
    @DELETE
    @Path("users/{name}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUser(@PathParam("name") final String userName) {
        Response response = null;
        // TODO auth + response

        try {
            ServiceObjectBuilder.getUserServiceObject().removeUser(userName); // TODO removeUser by ID!
            response = Response.status(200, USER_REMOVED).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch(rest.exceptions.UserDoesNotExistException e){
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
    @Path("users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("userId") final String userId) {
        Response response = null;
        // TODO auth + response

        try {
            User user =  ServiceObjectBuilder.getUserServiceObject().getUserById(userId); // TODO add this
            response = Response.status(200, MediaType.APPLICATION_JSON).type(MediaType.APPLICATION_JSON).entity(user).build();
        }
        catch(rest.exceptions.UserDoesNotExistException e){
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_USER_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }
}
