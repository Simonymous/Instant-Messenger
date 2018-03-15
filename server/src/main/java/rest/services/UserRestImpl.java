package rest.services;

import builder.ServiceObjectBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.classes.UserImpl;
import model.classes.UserQueryResponseImpl;
import model.interfaces.User;
import rest.classes.JSONClientAddress;
import rest.exceptions.UserDoesNotExistException;
import service.classes.ClientList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;

import static rest.constants.GeneralRestConstants.ERR_INTERNAL_SERVER_ERROR;
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

    /**
     *   Updates an existing User
     *   @return Http-Statuscode capsuled in Response
     */
    @Override
    @POST
    @Path("users/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("id") final String id, String json) {
        Response response = null;
        // TODO auth + response
        User user = makeUserFromJSON(json);
        try {
            ServiceObjectBuilder.getUserServiceObject().changeUserName(user.getUsername(), Integer.parseInt(id)); // TODO passwort kann nicht geändert werden.., changeUser? - refactor
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
        System.err.println("updateUser " + id + "(" + json + ")"); // TODO also prints changed pw
        return response;
    }

    /**
     *   Returns User for given Name
     *   @return User capsuled in Response
     */
    @Override
    @GET
    @Path("users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserByName(@DefaultValue("") @QueryParam("byname") String qustr) {
        Response response = null;
        // TODO auth + response
        if(qustr == null) { // TODO handle this
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
        System.err.println("getUserByName " + qustr);
        return response;
    }

    /**
     *   Returns User for given Name
     *   @return User capsuled in Response
     */
    @Override
    @GET
    @Path("users/name")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getTheUserByName(@QueryParam("byname") String name) {
        Response response = null;
        if(name == null) { // TODO handle this
            return Response.status(500).build();
        }
        try {
            User user =  ServiceObjectBuilder.getUserServiceObject().getUserByName(name);
            response = Response.ok(gSon.toJson(user)).build();
        }
        catch(rest.exceptions.UserDoesNotExistException e){
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_USER_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        System.err.println("getTheUserByName " + name);
        return response;
    }

    /**
     *   Adds new User
     *   @return Http-Statuscode capsuled in Response
     */
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
            User u = ServiceObjectBuilder.getUserServiceObject().addUser(user.getUsername(), user.getPassword());
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
        System.err.println("addUser " + json); // TODO also prints pw
        return response;
    }

    /**
     *   Removes an existing User
     *   @return Http-Statuscode capsuled in Response
     */
    @Override
    @DELETE
    @Path("users/{userid}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUser(@PathParam("userid") final String userId) {
        Response response = null;
        // TODO auth + response

        try {
            ServiceObjectBuilder.getUserServiceObject().removeUser(Integer.parseInt(userId));
            response = Response.status(200, USER_REMOVED).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch(rest.exceptions.UserDoesNotExistException e){
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_USER_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
            e.printStackTrace();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
            e.printStackTrace();
        }
        System.err.println("removeUser " + userId);
        return response;
    }

    /**
     *   Returns User for given Id
     *   @return User capsuled in Response
     */
    @Override
    @GET
    @Path("users/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserById(@PathParam("userId") final String userId) {
        Response response = null;
        // TODO auth + response

        try {
            User user =  ServiceObjectBuilder.getUserServiceObject().getUserById(Integer.parseInt(userId));
            response = Response.ok(gSon.toJson(user)).build();
        }
        catch(rest.exceptions.UserDoesNotExistException e){
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_USER_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        System.err.println("getUserById " + userId);
        return response;
    }

    /**
     * Initiate the notifying for the given InetAddress Object
     *
     * @param inet
     * @return
     */
    @Override
    @POST
    @Path("users/init")
    public Response initUpdate(String inet) {
        Response response = null;

        try{
            Gson gson = new GsonBuilder().create();
            JSONClientAddress jsonaddress = gson.fromJson(inet, JSONClientAddress.class);
            InetSocketAddress address = new InetSocketAddress(jsonaddress.address,jsonaddress.port);
            ClientList.getInstance().initUpdate(address);
            response = Response.status(200, CLIENT_NOTIFY_ADDED).type(MediaType.TEXT_HTML_TYPE).build();
        } catch (Exception e){
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    /**
     * Stops the notifying for the given InetAddress Object
     *
     * @param inet
     * @return
     */
    @Override
    @POST
    @Path("users/stop")
    public Response stopUpdate(String inet) {
        Response response = null;

        try{
            Gson gson = new GsonBuilder().create();
            JSONClientAddress jsonaddress = gson.fromJson(inet, JSONClientAddress.class);
            InetSocketAddress address = new InetSocketAddress(jsonaddress.address,jsonaddress.port);
            ClientList.getInstance().stopUpdate(address);
            response = Response.status(200, CLIENT_NOTIFY_REMOVED).type(MediaType.TEXT_HTML_TYPE).build();
        } catch (Exception e){
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    /**
     * Returns Groups for given user id
     * @param userId to get groups from
     * @return ArrayList of Groups capsuled in Response
     */
    @Override
    @GET
    @Path("users/{userId}/groups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsOfUser(@PathParam("userId") final String userId) {
        try {
            ArrayList<Integer> i = ServiceObjectBuilder.getUserServiceObject().getGroupIdsForUser(Integer.parseInt(userId));
            System.err.println("getGroupsOfUser " + userId + ": " + i.toString());
            return Response.ok(gSon.toJson(i)).build();
        } catch (UserDoesNotExistException e) {
            e.printStackTrace();
            return Response.status(404, ERR_USER_DOES_NOT_EXIST).build();
        }
    }
}
