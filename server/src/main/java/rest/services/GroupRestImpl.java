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

@Path(GroupRestImpl.webContextPath)
public class GroupRestImpl implements rest.interfaces.GroupRest {
    static final String webContextPath = "/im";
    private static final Gson gSon = new Gson();
    private Response.Status status;

    @Override
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

    @Override
    @DELETE
    @Path("removeGroup/{groupId}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeGroup(@PathParam("groupId") final int groupId) {
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

    @Override
    @POST
    @Path("addUserToGroup/{groupId}/{name}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUserToGroup(@PathParam("groupId") final int groupId, @PathParam("name") final String userName) {
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

    @Override
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

    @Override
    @GET
    @Path("getGroupById")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupById(@QueryParam("groupId") final int groupId) {
        Response response = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getGroupServiceObject().getGroupById(groupId));
            response = Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (GroupDoesNotExistException e) {
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @Override
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

    @Override
    @GET
    @Path("getUsersForGroup")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersForGroup(@QueryParam("groupId") int groupId) {
        Response response = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getGroupServiceObject().getUsersForGroup(groupId));
            response = Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (GroupDoesNotExistException e) {
            System.err.println(e.getMessage());
            response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).type(MediaType.TEXT_HTML_TYPE).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }
}
