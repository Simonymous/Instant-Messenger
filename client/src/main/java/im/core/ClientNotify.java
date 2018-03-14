package im.core;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path(ClientNotify.webContextPath)
public class ClientNotify {
    static final String webContextPath = "/im";
    private static final String GROUPS_UPDATED = "Groups updated.";
    private static final String MESSAGES_UPDATED = "Messages updated.";
    private static final String ERR_UPDATE_GROUPS = "Error while updating groups.";
    private static final String ERR_UPDATE_MESSAGES = "Error while updating messages.";

    /**
     * method for notifying, if there is a new group or a group was updated
     *
     * @return the response
     */
    @POST
    @Path("group/update")
    public Response updatedGroup() {
        Response response = null;
        try {
            new Updater().updateLocalGroups();
            response = Response.status(200, GROUPS_UPDATED).type(MediaType.TEXT_HTML_TYPE).build();
            System.out.println("Received Notify Delete Group from Server");
        } catch (Exception e) {
            response = Response.status(500, ERR_UPDATE_GROUPS).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    /**
     * method for notifying, if a group was deleted
     *
     * @param id the id of group which was deleted
     * @return the response
     */
    @POST
    @Path("group/delete/{id}")
    public Response removedGroup(@PathParam("id") final String id) {
        Response response = null;
        try {
            new Updater().deleteLocalGroup(id);
            response = Response.status(200, GROUPS_UPDATED).type(MediaType.TEXT_HTML_TYPE).build();
            System.out.println("Received Notify Delete Group from Server");
        } catch (Exception e) {
            response = Response.status(500, ERR_UPDATE_GROUPS).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    /**
     * method fot notifying, if there is a new message
     *
     * @param id the id of group, in which the message is new
     * @return the response
     */
    @POST
    @Path("message/{id}")
    public Response newMessage(@PathParam("id") final String id) {
        Response response = null;
        try {
            new Updater().updateMessagesForGroup(id);
            response = Response.status(200, MESSAGES_UPDATED).type(MediaType.TEXT_HTML_TYPE).build();
            System.out.println("Received Notify New Message from Server");
        } catch (Exception e) {
            response = Response.status(500, ERR_UPDATE_MESSAGES).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }
}