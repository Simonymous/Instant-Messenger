package rest.services;

import builder.ServiceObjectBuilder;
import com.google.gson.Gson;
import model.interfaces.Group;
import model.interfaces.Message;
import rest.exceptions.*;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.sql.SQLException;
import java.util.List;

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
    @GET
    @Path("groups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsByFilter(@QueryParam("byuser") List<String> users, @QueryParam("byname") String name) {
      // TODO implement
      return Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
    }

    @Override
    @POST
    @Path("groups")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGroup(Group gro) {
      Response response = null;
      // TODO auth (+ auth response)

      try {
        Group group = ServiceObjectBuilder.getGroupServiceObject().addNewGroup(gro.getGroupName());
        response = Response.status(200, GROUP_ADDED).type(MediaType.APPLICATION_JSON).entity(group).build();
      } catch (Exception e) {
        System.err.println(e);
        response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
      }

      return response;
    }

    @Override
    @DELETE
    @Path("groups/{groupId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeGroup(@PathParam("groupId") String groupId) {
      Response response = null;
      // TODO auth (+ auth response)

      try {
        ServiceObjectBuilder.getGroupServiceObject().removeGroup(Integer.parseInt(groupId));
        response = Response.status(200, GROUP_REMOVED).build();
      } catch(rest.exceptions.GroupDoesNotExistException e){
        System.err.println(e);
        response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
      } catch (Exception e) {
        System.err.println(e);
        response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
      }

      return response;
    }

    @Override
    @GET
    @Path("groups/{groupId}/users")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsersOfGroup(@PathParam("groupId") String groupId) {
      // TODO implement
        try {
            return Response.status(200).type(MediaType.APPLICATION_JSON).entity(ServiceObjectBuilder.getGroupServiceObject().getUserIdsForGroup(Integer.parseInt(groupId))).build();
        } catch (GroupDoesNotExistException e) {
            e.printStackTrace();
            return Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
        } catch (SQLException e) {
            e.printStackTrace();
            return Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
        }
    }

    @Override
    @POST
    @Path("groups/{groupId}/users/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addUserToGroup(@PathParam("groupId") String groupId, @PathParam("userId") String userId) {
      Response response = null;
      // TODO auth (+ auth response)

      try {
        ServiceObjectBuilder.getGroupServiceObject().addUserToGroup(Integer.parseInt(groupId), userId); // TODO addUserToGroup? - remove useless methods?
        response = Response.status(200, USER_ADDED_TO_GROUP).build();
      } catch(rest.exceptions.UserDoesNotExistException e){
        System.err.println(e);
        response = Response.status(404, ERR_USER_DOES_NOT_EXIST).build();
      } catch(rest.exceptions.GroupDoesNotExistException e){
        System.err.println(e);
        response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
      } catch (Exception e) {
        System.err.println(e);
        response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
      }

      return response;
    }

    @Override
    @DELETE
    @Path("groups/{groupId}/users/{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeUserFromGroup(@PathParam("groupId") String groupId, @PathParam("userId") String userId) {
      Response response = null;
      // TODO auth (+ auth response)

      try {
        ServiceObjectBuilder.getGroupServiceObject().removeUserFromGroup(Integer.parseInt(groupId), Integer.parseInt(userId)); // TODO removeUserFromGroup?
        response = Response.status(200, USER_ADDED_TO_GROUP).build();
      } catch(rest.exceptions.UserDoesNotExistException e){
        System.err.println(e);
        response = Response.status(404, ERR_USER_DOES_NOT_EXIST).build();
      } catch(rest.exceptions.GroupDoesNotExistException e){
        System.err.println(e);
        response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
      } catch (Exception e) {
        System.err.println(e);
        response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
      }

      return response;
    }

    @Override
    @GET
    @Path("groups/{groupId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupById(@PathParam("groupId") String groupId) {
      Response response = null;
      // TODO auth (+ auth response)

      try {
        Group group = ServiceObjectBuilder.getGroupServiceObject().getGroupById(Integer.parseInt(groupId));
        response = Response.status(200, GROUP_ADDED).type(MediaType.APPLICATION_JSON).entity(group).build();
      } catch (GroupDoesNotExistException e) {
        System.err.println(e);
        response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
      } catch (Exception e) {
        System.err.println(e);
        response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
      }

      return response;
    }

    @Override
    @POST
    @Path("groups/{groupId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response changeGroup(@PathParam("groupId") String groupId, Group group) {
      Response response = null;
      // TODO auth (+ auth response)

      try {
        ServiceObjectBuilder.getGroupServiceObject().changeGroupName(Integer.parseInt(groupId), group.getGroupName()); // TODO changeGroup? (should update only name for now)
        response = Response.status(200, GROUP_NAME_CHANGED).build(); // TODO make new constant
      } catch(rest.exceptions.GroupDoesNotExistException e){
        System.err.println(e);
        response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
      } catch (Exception e) {
        System.err.println(e);
        response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
      }

      return response;
    }

     @Override
     @GET
     @Path("groups/{groupId}/messages")
     @Produces(MediaType.APPLICATION_JSON)
     public Response getMessagesOfGroup(@PathParam("groupId") String groupId, @DefaultValue("0") @QueryParam("page") String page) {
       // TODO implement
     }

    @Override
    @POST
    @Path("groups/{groupId}/messages")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postMessage(@PathParam("groupId") String groupId, String message) {
      Response response = null;
      // TODO auth (+ auth response)

      try {
        ServiceObjectBuilder.getMessageServiceObject().addMessage(Integer.parseInt(groupId), message.getUser().getUserId(), message.getContent());
        response = Response.status(200, MESSAGE_ADDED).build(); // TODO make new constant
          // TODO addMessage must throw GroupDoesNotExistException!!!!!!!!
//      } catch(rest.exceptions.GroupDoesNotExistException e){
//        System.err.println(e);
//        response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
      } catch (Exception e) {
        System.err.println(e);
        response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
      }

      return response;
    }

}
