package rest.services;

import builder.ServiceObjectBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.classes.GroupImpl;
import model.interfaces.Group;
import model.interfaces.Message;
import model.interfaces.User;
import rest.classes.JSONGroup;
import rest.classes.JSONMessage;
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

    private JSONGroup makeGroupFromJSON(String json) {
        GsonBuilder b = new GsonBuilder();
        Gson gson = b.create();
        return gson.fromJson(json, JSONGroup.class);
    }

    private JSONMessage makeMessageFromJSON(String json) {
        GsonBuilder b = new GsonBuilder();
        Gson gson = b.create();
        return gson.fromJson(json, JSONMessage.class);
    }

    @Override
    @GET
    @Path("groups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsByFilter(@QueryParam("byuser") List<String> users, @QueryParam("byname") String name) {
      return Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
    }

    @Override
    @POST
    @Path("groups")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGroup(String gro) {
      Response response = null;
      // TODO auth (+ auth response)
      try {
        JSONGroup group = makeGroupFromJSON(gro);
        Group gr = ServiceObjectBuilder.getGroupServiceObject().addNewGroup(group.name);
        gr = ServiceObjectBuilder.getGroupServiceObject().getGroupById(gr.getGroupId());
        response = Response.ok(gSon.toJson(gr)).build();
      } catch (Exception e) {
        e.printStackTrace();
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
        try {
            return Response.ok(gSon.toJson(ServiceObjectBuilder.getGroupServiceObject().getUserIdsForGroup(Integer.parseInt(groupId)))).build();
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
        ServiceObjectBuilder.getGroupServiceObject().addUserToGroup(Integer.parseInt(groupId), Integer.parseInt(userId));
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
        ServiceObjectBuilder.getGroupServiceObject().removeUserFromGroup(Integer.parseInt(groupId), Integer.parseInt(userId));
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
        response = Response.ok(gSon.toJson(group)).build();
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
    public Response changeGroup(@PathParam("groupId") String groupId, String json) {
      Response response = null;
      // TODO auth (+ auth response)

      try {
        JSONGroup group = makeGroupFromJSON(json);
        ServiceObjectBuilder.getGroupServiceObject().changeGroupName(Integer.parseInt(groupId), group.name); // TODO changeGroup? (should update only name for now)
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
         try {
             return Response.ok(gSon.toJson(ServiceObjectBuilder.getMessageServiceObject().getMessagesForGroup(Integer.parseInt(groupId)))).build();
         } catch (GroupDoesNotExistException e) {
             e.printStackTrace();
             return Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
         } catch (Exception e) {
             e.printStackTrace();
            return Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
        }
     }

    @Override
    @POST
    @Path("groups/{groupId}/messages")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response postMessage(@PathParam("groupId") String groupId, String message) {
      Response response = null;
      // TODO auth (+ auth response)
      JSONMessage m = makeMessageFromJSON(message);

      try {
        ServiceObjectBuilder.getMessageServiceObject().addMessage(Integer.parseInt(groupId), m.userid, m.content);
        response = Response.status(200, MESSAGE_ADDED).build();
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
