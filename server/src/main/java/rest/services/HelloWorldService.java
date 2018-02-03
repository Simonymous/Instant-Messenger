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
    public Response getUserId(@QueryParam("name") final String userName) {
        status = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getUserServiceObject().getUserId(userName));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
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
    @Path("addUser/{name}/{password}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addUser(@PathParam("name") final String userName, @PathParam("password") final String password) {
        status = null;

        try {
            ServiceObjectBuilder.getUserServiceObject().addUser(userName, password);
            status = Response.Status.OK;
        }
        catch(rest.Exceptions.UserAlreadyExistsException e){
            System.err.println(e.getMessage());
            status = Response.Status.CONFLICT;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return Response.status(status).type(MediaType.TEXT_HTML_TYPE).build();
    }

    @DELETE
    @Path("removeUser/{name}")
    @Consumes("application/json")
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeUser(@PathParam("name") final String userName) {
        status = null;

        try {
            ServiceObjectBuilder.getUserServiceObject().removeUser(userName);
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
    @Path("getStatusForUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStatusForUser(@QueryParam("name") final String userName) {
        status = null;

        try {
            String json = gSon.toJson(ServiceObjectBuilder.getUserServiceObject().getStatusForUser(userName));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
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
    @Path("setStatusForUser/{name}/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response setStatusForUser(@PathParam("name") final String userName, @PathParam("status") final Boolean userStatus) {
        status = null;

        try {
            ServiceObjectBuilder.getUserServiceObject().setStatusForUser(userName, userStatus);
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
    @Path("getGroupsForUser")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getGroupsForUser(@QueryParam("name") final String userName) {
        status = null;

        try {
            String json =  gSon.toJson(ServiceObjectBuilder.getUserServiceObject().getGroupsForUser(userName));
            return Response.ok(json, MediaType.APPLICATION_JSON).build();
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

    //Group API
    //------------------------------------------------------------------------------------------------------------------

    @POST
    @Path("addNewGroup/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addNewGroup(@PathParam("name") final String groupName) {
        status = null;

        try {
            ServiceObjectBuilder.getGroupServiceObject().addNewGroup(groupName);
            status = Response.Status.OK;
        }
        catch (Exception e) {
            System.err.println(e.getMessage());
            status = Response.Status.INTERNAL_SERVER_ERROR;
        }
        return Response.status(status).type(MediaType.TEXT_HTML_TYPE).build();
    }

    @GET
    @Path("removeGroup")
    @Produces(MediaType.APPLICATION_JSON)
    public String removeGroup(@QueryParam("groupId") int groupId) {
        status = null;

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
        status = null;

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
        status = null;

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
        status = null;

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
        status = null;

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
        status = null;

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