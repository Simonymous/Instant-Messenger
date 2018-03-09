package rest.services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;

// models
import com.google.gson.reflect.TypeToken;
import model.classes.UserImpl;
import model.classes.UserQueryResponseImpl;
import model.interfaces.User;
import model.interfaces.UserQueryResponse;

// rest exceptions
import rest.exceptions.UserAlreadyExistsException;
import rest.exceptions.UserDoesNotExistException;

// public String constants
import static rest.constants.GeneralRestConstants.ERR_INTERNAL_SERVER_ERROR;
import static rest.constants.UserRestConstants.ERR_USER_ALREADY_EXISTS;
import static rest.constants.UserRestConstants.ERR_USER_DOES_NOT_EXIST;


public class UserRestClientImpl implements rest.interfaces.UserRestClient {

    private static final String URL = "http://localhost:4434";
    private static final String WEB_CONTEXT_PATH = "/im";
    private static final String USERS_PATH = "users";
    private static final String GROUPS_PATH = "groups";

    private Client client;
    private Response response;
    private Gson gSon;

    /**
     * default constructor
     */
    public UserRestClientImpl() {
        client = ClientBuilder.newClient();
        response = null;
    }

    /**
     * convert a json string to User object
     * @param json String to convert
     * @return User object
     */
    private User makeUserFromJSON(String json) {
        GsonBuilder b = new GsonBuilder();
        Gson gson = b.create();
        return gson.fromJson(json, UserImpl.class);
    }

    /**
     * send requst to change user
     * @param id user to change
     * @param json new user
     */
    public void updateUser(final String id, final String json) {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(USERS_PATH)
                    .path(id)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(String.class, json));

            if (response.getStatus() == 404) {
                throw new UserDoesNotExistException(ERR_USER_DOES_NOT_EXIST);

            } else if (response.getStatus() == 409) {
                throw new UserAlreadyExistsException(ERR_USER_ALREADY_EXISTS);

            } else if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());

            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
    }

    /**
     * send request to get a user object by name
     * @param name - name of user to get
     * @return User
     */
    public UserQueryResponse getUserByName(final String name) {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(USERS_PATH)
                    .queryParam("byname", name)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Response.class);


            if (response.getStatus() == 404) {
                throw new UserDoesNotExistException(ERR_USER_DOES_NOT_EXIST);

            } else if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());

            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }

        String json = (String) response.readEntity(String.class);
        gSon = new GsonBuilder().create();

        return gSon.fromJson(json, UserQueryResponseImpl.class);
    }

    /**
     * send request to get a user object by name
     * @return Userlist
     */
    public UserQueryResponse getAllUser() {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(USERS_PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Response.class);

            if (response.getStatus() == 404) {
                throw new UserDoesNotExistException(ERR_USER_DOES_NOT_EXIST);

            } else if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());

            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }

        String json = (String) response.readEntity(String.class);
        gSon = new GsonBuilder().create();

        return gSon.fromJson(json, UserQueryResponseImpl.class);
    }

    /**
     * send request to add new user
     * @param json new user to add
     * @return User object added
     */
    public User addUser(String json) {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(USERS_PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(String.class, json));

            if (response.getStatus() == 409) {
                throw new UserAlreadyExistsException(ERR_USER_ALREADY_EXISTS);

            } else if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());

            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }

        String respJson = (String) response.readEntity(String.class);
        return makeUserFromJSON(respJson);
    }

    /**
     * send request to remove user by name
     * @param userName user to remove
     */
    public void removeUser(final String userName) {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(USERS_PATH)
                    .path(userName)
                    .request(MediaType.APPLICATION_JSON)
                    .delete();

            if (response.getStatus() == 404) {
                throw new UserDoesNotExistException(ERR_USER_DOES_NOT_EXIST);

            } else if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());

            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
    }

    /**
     * send request to get user by id
     * @param userId - user id to look for
     * @return User object
     */
    public User getUserById(final String userId) {
        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(USERS_PATH)
                    .path(userId)
                    //.queryParam("byid", id)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Response.class);


            if (response.getStatus() == 404) {
                throw new UserDoesNotExistException(ERR_USER_DOES_NOT_EXIST);

            } else if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_INTERNAL_SERVER_ERROR + ": " + response.getStatus());

            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }

        String respJson = (String) response.readEntity(String.class);
        return makeUserFromJSON(respJson);
    }

    /**
     * send request to get all groups of user
     * @param userId user to get groups from
     * @return ArrayList of group ids
     */
    public ArrayList<Integer> getGroupsOfUser(final String userId) {
        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(USERS_PATH)
                    .path(userId)
                    .path(GROUPS_PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .get(Response.class);


            if (response.getStatus() == 404) {
                throw new UserDoesNotExistException(ERR_USER_DOES_NOT_EXIST);

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

}