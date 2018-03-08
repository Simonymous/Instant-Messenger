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
import model.interfaces.Message;
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

    /**
     * default constructor
     */
    public GroupRestClientImpl() {
        client = ClientBuilder.newClient();
        response = null;
    }

    /**
     * convert a json string to Group object
     * @param json String to convert
     * @return Group object
     */
    private Group makeGroupFromJSON(String json) {
        GsonBuilder b = new GsonBuilder();
        Gson gson = b.create();
        return gson.fromJson(json, GroupImpl.class);
    }

    /**
     * send a requst to add a new group
     * @param gro new Group object to add
     * @return added Group object
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

    /**
     * send a delete request for group with given id
     * @param groupId  groupId to indentify group
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

    /**
     * get a list of all users in a group
     * @param groupId
     * @return ArrayList with userIds
     */
    public ArrayList<Integer> getUsersOfGroup(final String groupId) {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(GROUPS_PATH)
                    .path(groupId)
                    .path(USERS_PATH)
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

    /**
     * send request to add new user to group
     * @param groupId  group to add user to
     * @param userId user to add
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

    /**
     * send request to remove user from group
     * @param groupId group to remove user from
     * @param userId user to remove
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

    /**
     * send request to get a group
     * @param groupId id of group to get
     * @return Group object
     */
    public Group getGroupById(final String groupId) {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(GROUPS_PATH)
                    .path(groupId)
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

    /**
     * send request to change a group
     * @param groupId group to change
     * @param group new changes Group object
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

    /**
     * send request to get all messages within a group
     * @param groupId group to get messages from
     * @param page select which page of messages to get
     * @return ArrayList of Messages in group
     */
    public ArrayList<Message> getMessagesOfGroup(final String groupId, final String page) {
        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(GROUPS_PATH)
                    .path(groupId)
                    .path(MESSAGES_PATH)
                    .queryParam("page", page)
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

        return gSon.fromJson(json, new TypeToken<ArrayList<Message>>(){}.getType());
    }

    /**
     * send request to post a message
     * @param groupId group to post message to
     * @param jsonMessage message to post
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

}






