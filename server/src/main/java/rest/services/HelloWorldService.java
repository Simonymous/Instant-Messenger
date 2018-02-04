package rest.services;

import builder.ServiceObjectBuilder;
import com.google.gson.Gson;
import service.exceptions.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static rest.constants.GeneralRestConstants.*;
import static rest.constants.GroupRestConstants.*;
import static rest.constants.UserRestConstants.*;


@Path(HelloWorldService.webContextPath)
public class HelloWorldService {
    static final String webContextPath = "/im";
    private static final Gson gSon = new Gson();
    private Response.Status status;

    //User API
    //------------------------------------------------------------------------------------------------------------------

    @GET
    @Path("doesUserExist")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response doesUserExist(@QueryParam("name") String name) {
        Response response = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getUserServiceObject().doesUserExist(name));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

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

    @GET
    @Path("validCredentials")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validCredentials(@QueryParam("name") final String userName, @QueryParam("password") final String password) {
        Response response = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getUserServiceObject().validCredentials(userName, password));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }

        return response;
    }

    @GET
    @Path("getUserId")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUserId(@QueryParam("name") final String userName) {
        Response response = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getUserServiceObject().getUserId(userName));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
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

    @GET
    @Path("getStatusForUser")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatusForUser(@QueryParam("name") final String userName) {
        Response response = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getUserServiceObject().getStatusForUser(userName));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
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

    @GET
    @Path("getGroupsForUser")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsForUser(@QueryParam("name") final String userName) {
        Response response = null;

        try {
            String json =  gSon.toJson(ServiceObjectBuilder.getUserServiceObject().getGroupsForUser(userName));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
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

    //Group API
    //------------------------------------------------------------------------------------------------------------------

    @POST
    @Path("addNewGroup/{name}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewGroup(@PathParam("name") final String groupName) {
        Response response = null;

        try {
            ServiceObjectBuilder.getGroupServiceObject().addNewGroup(groupName);
            response = Response.status(200, GROUP_ADDED).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @DELETE
    @Path("removeGroup/{groupId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeGroup(@PathParam("groupId") int groupId) {
        Response response = null;

        try {
            ServiceObjectBuilder.getGroupServiceObject().removeGroup(groupId);
            response = Response.status(200, GROUP_REMOVED).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch(rest.exceptions.GroupDoesNotExistException e){
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @POST
    @Path("addUserToGroup/{groupId}/{name}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUserToGroup(@PathParam("groupId") int groupId, @PathParam("name") String userName) {
        Response response = null;

        try {
            ServiceObjectBuilder.getGroupServiceObject().addUserToGroup(groupId, userName);
            response = Response.status(200, USER_ADDED_TO_GROUP).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch(rest.exceptions.UserDoesNotExistException e){
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_USER_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch(rest.exceptions.GroupDoesNotExistException e){
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @DELETE
    @Path("removeUserFromGroup/{userId}/{groupId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUserFromGroup(@PathParam("userId") final int userId, @PathParam("groupId") final int groupId) {
        Response response = null;

        try {
            ServiceObjectBuilder.getGroupServiceObject().removeUserFromGroup(groupId, userId);
            response = Response.status(200, USER_REMOVED_FROM_GROUP).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch(rest.exceptions.UserDoesNotExistException e){
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_USER_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch(rest.exceptions.GroupDoesNotExistException e){
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @GET
    @Path("getGroupById")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupById(@QueryParam("groupId") final int groupId) {
        Response response = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getGroupServiceObject().getGroupById(groupId));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (GroupDoesNotExistException e) {
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @POST
    @Path("changeGroupName/{groupId}/{name}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeGroupName(@PathParam("groupId") final int groupId, @PathParam("name") final String newName) {
        Response response = null;

        try {
            ServiceObjectBuilder.getGroupServiceObject().changeGroupName(groupId, newName);
            response = Response.status(200, GROUP_NAME_CHANGED).type(MediaType.TEXT_HTML_TYPE).build();
        } catch (GroupDoesNotExistException e) {
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @GET
    @Path("getUsersForGroup")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersForGroup(@QueryParam("groupId") int groupId) {
        Response response = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getGroupServiceObject().getUsersForGroup(groupId));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (GroupDoesNotExistException e) {
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    //Message API
    //------------------------------------------------------------------------------------------------------------------
    @POST
    @Path("addMesssage/{groupId}/{userId}/{content}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMesssage(@PathParam("groupId") final int groupId, @PathParam("userId") final int userId, @PathParam("content") final String content){

    }


}