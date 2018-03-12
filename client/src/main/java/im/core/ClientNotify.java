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

    @POST
    @Path("group/new")
    public Response newGroup() {
        Response response = null;
        try {
            new Updater().updateLocalGroups();
            response = Response.status(200, GROUPS_UPDATED).type(MediaType.TEXT_HTML_TYPE).build();
        } catch (Exception e) {
            response = Response.status(500, ERR_UPDATE_GROUPS).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @POST
    @Path("group/deleted")
    public Response removedGroup() {
        Response response = null;
        try {
            new Updater().updateLocalGroups();
            response = Response.status(200, GROUPS_UPDATED).type(MediaType.TEXT_HTML_TYPE).build();
        } catch (Exception e) {
            response = Response.status(500, ERR_UPDATE_GROUPS).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }

    @POST
    @Path("message/{id}")
    public Response newMessage(@PathParam("id") final String id) {
        Response response = null;
        try {
            new Updater().updateMessagesForGroup(id);
            response = Response.status(200, MESSAGES_UPDATED).type(MediaType.TEXT_HTML_TYPE).build();
        } catch (Exception e) {
            response = Response.status(500, ERR_UPDATE_MESSAGES).type(MediaType.TEXT_HTML_TYPE).build();
        }
        return response;
    }
}
