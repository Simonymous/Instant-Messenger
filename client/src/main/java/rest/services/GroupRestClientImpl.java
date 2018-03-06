package rest.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

// models
import model.interfaces.Group;
import model.classes.GroupImpl;

// rest exceptions
import rest.exceptions.GroupDoesNotExistException;
import rest.exceptions.UserOrGroupDoesNotExistException;

// public String constants
import static rest.constants.GeneralRestConstants.ERR_INTERNAL_SERVER_ERROR;
import static rest.constants.GroupRestConstants.ERR_GROUP_DOES_NOT_EXIST;
import static rest.constants.GroupRestConstants.ERR_USER_GROUP_DOES_NOT_EXIST;


public class GroupRestClientImpl implements rest.interfaces.GroupRestClient {

    private static final String URL = "http://localhost:4434";
    private static final String WEB_CONTEXT_PATH = "/im";
    private static final String GROUPS_PATH = "groups";
    private static final String USERS_PATH = "users";
    private static final String MESSAGES_PATH = "messages";

    private Client client;
    private Response response;
    private Gson gSon;


    public GroupRestClientImpl() {
        client = ClientBuilder.newClient();
        response = null;
    }

    private Group makeGroupFromJSON(String json) {
        GsonBuilder b = new GsonBuilder();
        Gson gson = b.create();
        return gson.fromJson(json, GroupImpl.class);
    }

    /*
     * TODO: implement getGroupsByFilter()
     */
    /*
    @Override
    @GET
    @Path("groups")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsByFilter(@QueryParam("byuser") List<String> users, @QueryParam("byname") String name) {
        // TODO implement
        return Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
    }
    */

    public Group addGroup(Group gro) {
        gSon = new GsonBuilder().create();
        String json = gSon.toJson(gro);

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(GROUPS_PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(String.class, json));

            if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }

        String respJson = response.readEntity(String.class);

        return makeGroupFromJSON(respJson);
    }
    /*
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
    */

    public void removeGroup(final String groupId) {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(GROUPS_PATH)
                    .path(groupId)
                    .request(MediaType.APPLICATION_JSON)
                    .delete();

            if (response.getStatus() == 404) {
                throw new GroupDoesNotExistException(ERR_GROUP_DOES_NOT_EXIST);

            } else if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());

            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
    }
    /*
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
        } catch(GroupDoesNotExistException e){
            System.err.println(e);
            response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
        } catch (Exception e) {
            System.err.println(e);
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }
    */

    public ArrayList<Integer> getUsersOfGroup(final String groupId) {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(GROUPS_PATH)
                    .path(groupId)
                    .path(USERS_PATH)
                    //.queryParam("byname", name)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Response.class);


            if (response.getStatus() == 404) {
                throw new GroupDoesNotExistException(ERR_GROUP_DOES_NOT_EXIST);

            } else if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());

            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }

        String json = (String) response.readEntity(String.class);
        gSon = new GsonBuilder().create();

        return gSon.fromJson(json, new TypeToken<ArrayList<Integer>>(){}.getType());
    }
    /*
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
    */

    public void addUserToGroup(final String groupId, final String userId) {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(GROUPS_PATH)
                    .path(groupId)
                    .path(USERS_PATH)
                    .path(userId)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(null));

            if (response.getStatus() == 404) {
                throw new UserOrGroupDoesNotExistException(ERR_USER_GROUP_DOES_NOT_EXIST);

            } else if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
    }
    /*
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
        } catch(UserDoesNotExistException e){
            System.err.println(e);
            response = Response.status(404, ERR_USER_DOES_NOT_EXIST).build();
        } catch(GroupDoesNotExistException e){
            System.err.println(e);
            response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
        } catch (Exception e) {
            System.err.println(e);
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }
    */

    public void removeUserFromGroup(final String groupId, final String userId) {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(GROUPS_PATH)
                    .path(groupId)
                    .path(USERS_PATH)
                    .path(userId)
                    .request(MediaType.APPLICATION_JSON)
                    .delete();

            if (response.getStatus() == 404) {
                throw new UserOrGroupDoesNotExistException(ERR_USER_GROUP_DOES_NOT_EXIST);

            } else if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());

            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
    }
    /*
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
        } catch(UserDoesNotExistException e){
            System.err.println(e);
            response = Response.status(404, ERR_USER_DOES_NOT_EXIST).build();
        } catch(GroupDoesNotExistException e){
            System.err.println(e);
            response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
        } catch (Exception e) {
            System.err.println(e);
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }
    */

    public Group getGroupById(final String groupId) {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(GROUPS_PATH)
                    .path(groupId)
                    //.queryParam("byname", name)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Response.class);


            if (response.getStatus() == 404) {
                throw new GroupDoesNotExistException(ERR_GROUP_DOES_NOT_EXIST);

            } else if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());

            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }

        String json = (String) response.readEntity(String.class);
        gSon = new GsonBuilder().create();

        return gSon.fromJson(json, Group.class);
    }
    /*
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
    */

    public void changeGroup(final String groupId, Group group) {
        gSon = new GsonBuilder().create();
        String json = gSon.toJson(group);

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(GROUPS_PATH)
                    .path(groupId)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(String.class, json));

            if (response.getStatus() == 404) {
                throw new GroupDoesNotExistException(ERR_GROUP_DOES_NOT_EXIST);

            } else if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
    }
    /*
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
        } catch(GroupDoesNotExistException e){
            System.err.println(e);
            response = Response.status(404, ERR_GROUP_DOES_NOT_EXIST).build();
        } catch (Exception e) {
            System.err.println(e);
            response = Response.status(500, ERR_INTERNAL_SERVER_ERROR).build();
        }

        return response;
    }
    */

    /*
     * TODO: implement getMessagesOfGroup()
     */
    /*
    @Override
    @GET
    @Path("groups/{groupId}/messages")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMessagesOfGroup(@PathParam("groupId") String groupId, @DefaultValue("0") @QueryParam("page") String page) {
        // TODO implement
    }
    */



    public void postMessage(final String groupId, final String jsonMessage) {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(GROUPS_PATH)
                    .path(groupId)
                    .path(MESSAGES_PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(String.class, jsonMessage));

            if (response.getStatus() == 404) {
                throw new GroupDoesNotExistException(ERR_GROUP_DOES_NOT_EXIST);

            } else if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
    }
    /*
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
    */

}






