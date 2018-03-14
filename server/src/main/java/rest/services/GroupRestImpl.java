package rest.services;

import builder.ServiceObjectBuilder;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.interfaces.Group;
import rest.classes.JSONGroup;
import rest.classes.JSONMessage;
import rest.exceptions.GroupDoesNotExistException;
import service.classes.ClientList;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

import static rest.constants.GeneralRestConstants.ERR_INTERNAL_SERVER_ERROR;
import static rest.constants.GroupRestConstants.*;
import static rest.constants.MessageRestConstants.MESSAGE_ADDED;
import static rest.constants.UserRestConstants.ERR_USER_DOES_NOT_EXIST;

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

    /**
     *   Adds new Group
     *   @return Http-Statuscode capsuled in Response
     */
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
        ClientList.getInstance().notifyUpdateGroup();
        response = Response.ok(gSon.toJson(gr)).build();
      } catch (Exception e) {
        e.printStackTrace();
        response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
      }

      System.err.println("addGroup " + gro);
      return response;
    }

    /**
     *   Removes existing Group
     *   @return Http-Statuscode capsuled in Response
     */
    @Override
    @DELETE
    @Path("groups/{groupId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeGroup(@PathParam("groupId") String groupId) {
      Response response = null;
      // TODO auth (+ auth response)

      try {
        ServiceObjectBuilder.getGroupServiceObject().removeGroup(Integer.parseInt(groupId));
        ClientList.getInstance().notifyRemoveGroup(groupId);
        response = Response.status(200, GROUP_REMOVED).build();
      } catch(rest.exceptions.GroupDoesNotExistException e){
        System.err.println(e);
        response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
      } catch (Exception e) {
        System.err.println(e);
        response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
      }

      System.err.println("removeGroup " + groupId);
      return response;
    }

    /**
     *   Returns Users for Group
     *   @return User-List capsuled in Response
     */
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

    /**
     *   Adds User to Group
     *   @return Http-Statuscode capsuled in Response
     */
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

      System.err.println("addUserToGroup " + groupId + " <-- " + userId);
      return response;
    }

    /**
     *   Removes User from Group
     *   @return Http-Statuscode capsuled in Response
     */
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

      System.err.println("removeUserFromGroup " + groupId + " <-X- " + userId);
      return response;
    }

    /**
     *   Returns Group by given Id
     *   @return Group capsuled in Response
     */
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

      System.err.println("getGroupById " + groupId);
      return response;
    }

    /**
     *   Changes existing Group
     *   @return Http-Statuscode capsuled in Response
     */
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
          ClientList.getInstance().notifyUpdateGroup();
          response = Response.status(200, GROUP_NAME_CHANGED).build(); // TODO make new constant
      } catch(rest.exceptions.GroupDoesNotExistException e){
        System.err.println(e);
        response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
      } catch (Exception e) {
        System.err.println(e);
        response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
      }

      System.err.println("changeGroup " + groupId + "(" + json + ")");
      return response;
    }

    /**
     *   Returns Message for given Group
     *   @return Message-List capsuled in Response
     */
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

    /**
     *   Adds new Message to Group
     *   @return Http-Statuscode capsuled in Response
     */
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
        ClientList.getInstance().notifyNewMessage(groupId);
        response = Response.status(200, MESSAGE_ADDED).build();
          // TODO addMessage must throw GroupDoesNotExistException!!!!!!!!
//      } catch(rest.exceptions.GroupDoesNotExistException e){
//        System.err.println(e);
//        response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
      } catch (Exception e) {
        System.err.println(e);
        response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
      }

      System.err.println("postMessage " + groupId + " << " + message);
      return response;
    }

}
