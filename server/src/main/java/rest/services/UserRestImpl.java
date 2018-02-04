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

@Path(UserRestImpl.webContextPath)
public class UserRestImpl implements rest.interfaces.UserRest {
    static final String webContextPath = "/im";
    private static final Gson gSon = new Gson();
    private Response.Status status;

    @Override
    @GET
    @Path("doesUserExist")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response doesUserExist(@QueryParam("name") String name) {
        Response response = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getUserServiceObject().doesUserExist(name));
            response = Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @Override
    @POST
    @Path("changeUserName/{newName}/{oldName}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeUserName(@PathParam("newName") final String newName, @PathParam("oldName") final String oldName) {
        Response response = null;

        try {
            ServiceObjectBuilder.getUserServiceObject().changeUserName(newName, oldName);
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
    @POST
    @Path("changeUserPassword/{name}/{password}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeUserPassword(@PathParam("name") final String userName, @PathParam("password") final String password) {
        Response response = null;

        try {
            ServiceObjectBuilder.getUserServiceObject().changeUserPassword(userName, password);
            response = Response.status(200, USER_PASSWORD_CHANGED).type(MediaType.TEXT_HTML_TYPE).build();
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
    @Path("validCredentials")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validCredentials(@QueryParam("name") final String userName, @QueryParam("password") final String password) {
        Response response = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getUserServiceObject().validCredentials(userName, password));
            response = Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }

        return response;
    }

    @Override
    @GET
    @Path("getUserId")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserId(@QueryParam("name") final String userName) {
        Response response = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getUserServiceObject().getUserId(userName));
            response = Response.ok(json, MediaType.APPLICATION_JSON).build();
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
    @Path("addUser/{name}/{password}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@PathParam("name") final String userName, @PathParam("password") final String password) {
        Response response = null;

        try {
            ServiceObjectBuilder.getUserServiceObject().addUser(userName, password);
            response = Response.status(200, USER_ADDED).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch(rest.exceptions.UserAlreadyExistsException e){
            System.err.println(e.getMessage());
            response = Response.status(409, ERR_USER_ALREADY_EXISTS).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @Override
    @DELETE
    @Path("removeUser/{name}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUser(@PathParam("name") final String userName) {
        Response response = null;

        try {
            ServiceObjectBuilder.getUserServiceObject().removeUser(userName);
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
    @Path("getStatusForUser")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatusForUser(@QueryParam("name") final String userName) {
        Response response = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getUserServiceObject().getStatusForUser(userName));
            response = Response.ok(json, MediaType.APPLICATION_JSON).build();
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
    @Path("setStatusForUser/{name}/{status}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setStatusForUser(@PathParam("name") final String userName, @PathParam("status") final Boolean userStatus) {
        Response response = null;

        try {
            ServiceObjectBuilder.getUserServiceObject().setStatusForUser(userName, userStatus);
            response = Response.status(200, STATUS_CHANGED).type(MediaType.TEXT_HTML_TYPE).build();
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
    @Path("getGroupsForUser")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsForUser(@QueryParam("name") final String userName) {
        Response response = null;

        try {
            String json =  gSon.toJson(ServiceObjectBuilder.getUserServiceObject().getGroupsForUser(userName));
            response = Response.ok(json, MediaType.APPLICATION_JSON).build();
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
