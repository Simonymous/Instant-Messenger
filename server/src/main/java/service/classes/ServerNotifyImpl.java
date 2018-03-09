package service.classes;

import service.interfaces.ServerNotify;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * Class to notify the client if groups or messages change
 */
public class ServerNotifyImpl implements ServerNotify {

    private static final String ERR_UPDATE_GROUPS = "Error while updating groups.";
    private static final String ERR_UPDATE_MESSAGES = "Error while updating messages.";

    private static final String URL = "http://localhost:4434";
    private static final String WEB_CONTEXT_PATH = "/im";
    private static final String GROUPS_PATH = "group";
    private static final String MESSAGES_PATH = "message";
    private static final String NEW_PATH = "new";
    private static final String DELETED_PATH = "deleted";

    private Client client;
    private Response response;

    /**
     * default constructor
     */
    public ServerNotifyImpl() {
        client = ClientBuilder.newClient();
        response = null;
    }

    /**
     * notifies the client to update groups due to added group
     */
    public void notifyNewGroup() {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(GROUPS_PATH)
                    .path(NEW_PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(null));

            if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_UPDATE_GROUPS + ": " + response.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
    }

    /**
     * notifies the client to update groups due to removed group
     */
    public void notifyRemovedGroup() {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(GROUPS_PATH)
                    .path(DELETED_PATH)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(null));

            if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_UPDATE_GROUPS + ": " + response.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
    }

    /**
     * notifies the client to update messages
     * @param id - message id
     */
    public void notifyNewMessage(final String id) {

        try {
            response = client
                    .target(URL + WEB_CONTEXT_PATH)
                    .path(MESSAGES_PATH)
                    .path(id)
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.json(null));

            if (response.getStatus() == 500) {
                throw new RuntimeException(ERR_UPDATE_MESSAGES + ": " + response.getStatus());
            }

        } catch (Exception e) {
            e.printStackTrace();
            // TODO: Handle exception
        }
    }

}