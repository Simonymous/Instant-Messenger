package rest.services;

import builder.ServiceObjectBuilder;
import com.google.gson.Gson;
import model.interfaces.Group;
import service.exceptions.*;

import javax.annotation.Generated;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import java.sql.SQLException;
import java.util.ArrayList;

import java.net.HttpURLConnection;

import static rest.constants.GroupRestConstants.*;
import static rest.constants.UserRestConstants.*;

//TODO In mehere Klassen aufteilen die Interfaces implementieren
//TODO @GET @POST @DELETE richtig verwenden
//TODO Throws in relevanten Dao und Modelklassen hinzufügen
//TODO Übergabeparameter als Json
//TODO Fehlermeldungen als Json (Eventuell Exceptions zu Json konvertieren)
@Path(HelloWorldService.webContextPath)
public class HelloWorldService {
    static final String webContextPath = "/im";
    private static final Gson gSon = new Gson();
    private Response.Status status;

//    @GET @Path("hallo") @Produces( MediaType.TEXT_PLAIN )
//    public String halloPlainText( @QueryParam("name") String name )
//    {
//        return "Plain-Text: Hallo " + name;
//    }
//
//    @GET @Produces( MediaType.TEXT_HTML )
//    public String halloHtml( @QueryParam("name") String name )
//    {
//        return "<html><title>HelloWorld</title><body><h2>Html: Hallo " + name + "</h2></body></html>";
//    }

    //User API
    //------------------------------------------------------------------------------------------------------------------

    @GET
    @Path("doesUserExist")
    @Produces(MediaType.APPLICATION_JSON)
    public Response doesUserExist(@QueryParam("name") String name) {
        status = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getUserServiceObject().doesUserExist(name));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return Response.status(status).type(MediaType.TEXT_HTML_TYPE).build();
    }

    @POST
    @Path("changeUserName/{newName}/{oldName}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeUserName(@PathParam("newName") final String newName, @PathParam("oldName") final String oldName) {
        status = null;

        try {
            ServiceObjectBuilder.getUserServiceObject().changeUserName(newName, oldName);
            status = Response.Status.OK;
        }
        catch(rest.Exceptions.UserAlreadyExistsException e){
            System.err.println(e.getMessage());
            status = Response.Status.CONFLICT;
        }
        catch(rest.Exceptions.UserDoesNotExistException e){
            System.err.println(e.getMessage());
            status = Response.Status.NOT_FOUND;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return Response.status(status).type(MediaType.TEXT_HTML_TYPE).build();
    }

    @POST
    @Path("changeUserPassword/{name}/{password}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response changeUserPassword(@PathParam("name") final String userName, @PathParam("password") final String password) {
        status = null;

        try {
            ServiceObjectBuilder.getUserServiceObject().changeUserPassword(userName, password);
            status = Response.Status.OK;
        }
        catch(rest.Exceptions.UserDoesNotExistException e){
            System.err.println(e.getMessage());
            status = Response.Status.NOT_FOUND;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return Response.status(status).type(MediaType.TEXT_HTML_TYPE).build();
    }

    @GET
    @Path("validCredentials")
    @Produces(MediaType.APPLICATION_JSON)
    public Response validCredentials(@QueryParam("name") final String userName, @QueryParam("password") final String password) {
        status = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getUserServiceObject().validCredentials(userName, password));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }

        return Response.status(status).type(MediaType.TEXT_HTML_TYPE).build();
    }

    @GET
    @Path("getUserId")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUserId(@QueryParam("name") String userName) {
        status = null;

        try {
            return gSon.toJson(ServiceObjectBuilder.getUserServiceObject().getUserId(userName));
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            return gSon.toJson(e);
        }
    }

    @POST
    @Path("addUser")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public void addUser(@QueryParam("name") String userName, @QueryParam("password") String password) {
        status = null;

        try {
            ServiceObjectBuilder.getUserServiceObject().addUser(userName, password);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @DELETE
    @Path("removeUser")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public void removeUser(@QueryParam("name") String userName) {
        status = null;

        try {
            ServiceObjectBuilder.getUserServiceObject().removeUser(userName);
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    @GET
    @Path("getStatusForUser")
    @Produces(MediaType.APPLICATION_JSON)
    public String getStatusForUser(@QueryParam("name") String userName) {
        status = null;

        try {
            return gSon.toJson(ServiceObjectBuilder.getUserServiceObject().getStatusForUser(userName));
        } catch (UserDoesNotExistException e) {
            System.err.println(e.getMessage());
            return ERR_USER_DOES_NOT_EXIST;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET
    @Path("setStatusForUser")
    @Produces(MediaType.APPLICATION_JSON)
    public String setStatusForUser(@QueryParam("name") String userName, @QueryParam("status") Boolean status) {
        status = null;

        try {
            ServiceObjectBuilder.getUserServiceObject().setStatusForUser(userName, status);
            return STATUS_CHANGED;
        } catch (UserDoesNotExistException e) {
            System.err.println(e.getMessage());
            return ERR_USER_DOES_NOT_EXIST;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET
    @Path("getGroupsForUser")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGroupsForUser(@QueryParam("name") String userName) {
        status = null;

        try {
            return gSon.toJson(ServiceObjectBuilder.getUserServiceObject().getGroupsForUser(userName));
        } catch (UserDoesNotExistException e) {
            System.err.println(ERR_USER_DOES_NOT_EXIST);
            return ERR_USER_DOES_NOT_EXIST;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    //Group API
    //------------------------------------------------------------------------------------------------------------------

    @GET
    @Path("addNewGroup")
    @Produces(MediaType.APPLICATION_JSON)
    public String addNewGroup(@QueryParam("name") String groupName) {
        try {
            ServiceObjectBuilder.getGroupServiceObject().addNewGroup(groupName);
            return GROUP_ADDED;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET
    @Path("removeGroup")
    @Produces(MediaType.APPLICATION_JSON)
    public String removeGroup(@QueryParam("groupId") int groupId) {
        try {
            ServiceObjectBuilder.getGroupServiceObject().removeGroup(groupId);
            return GROUP_REMOVED;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET
    @Path("addUserToGroup")
    @Produces(MediaType.APPLICATION_JSON)
    public String addUserToGroup(@QueryParam("groupId") int groupId, @QueryParam("name") String userName) {
        try {
            ServiceObjectBuilder.getGroupServiceObject().addUserToGroup(groupId, userName);
            return USER_ADDED_TO_GROUP;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET
    @Path("removeUserFromGroup")
    @Produces(MediaType.APPLICATION_JSON)
    public String removeUserFromGroup(@QueryParam("userId") int userId, @QueryParam("groupId") int groupId) {
        try {
            ServiceObjectBuilder.getGroupServiceObject().removeUserFromGroup(groupId, userId);
            return USER_REMOVED_FROM_GROUP;
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET
    @Path("getGroupById")
    @Produces(MediaType.APPLICATION_JSON)
    public String getGroupById(@QueryParam("groupId") int groupId) {
        try {
            return gSon.toJson(ServiceObjectBuilder.getGroupServiceObject().getGroupById(groupId));
        } catch (GroupDoesNotExistException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET
    @Path("changeGroupName")
    @Produces(MediaType.APPLICATION_JSON)
    public String changeGroupName(@QueryParam("groupId") int groupId, @QueryParam("name") String newName) {
        try {
            ServiceObjectBuilder.getGroupServiceObject().changeGroupName(groupId, newName);
            return GROUP_NAME_CHANGED;
        } catch (GroupDoesNotExistException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET
    @Path("getUsersForGroup")
    @Produces(MediaType.APPLICATION_JSON)
    public String getUsersForGroup(@QueryParam("groupId") int groupId) {
        try {
            return gSon.toJson(ServiceObjectBuilder.getGroupServiceObject().getUsersForGroup(groupId));
        } catch (GroupDoesNotExistException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }
}