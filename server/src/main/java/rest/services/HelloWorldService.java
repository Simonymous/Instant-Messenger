package rest.services;

import builder.ServiceObjectBuilder;
import com.google.gson.Gson;
import model.interfaces.Group;
import service.exceptions.*;

import javax.annotation.Generated;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

import java.sql.SQLException;
import java.util.ArrayList;

import static rest.constants.UserRestConstants.*;

//TODO In mehere Klassen aufteilen die Interfaces implementieren
//TODO Nach dem Testen Methooden bei denen das Password übertragen wird zu @Post Methode umbauen
//TODO Throws in relevanten Dao und Modelklassen hinzufügen
@Path( HelloWorldService.webContextPath )
public class HelloWorldService
{
    static final String webContextPath = "/im";

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

    @GET @Path("doesUserExist")
    public String doesUserExist( @QueryParam("name") String name){
        try {
            return new Gson().toJson(ServiceObjectBuilder.getUserServiceObject().doesUserExist(name));
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET @Path("changeUserName")
    public String changeUserName( @QueryParam("newName") String newName, @QueryParam("oldName") String oldName){
        try {
            ServiceObjectBuilder.getUserServiceObject().changeUserName(newName, oldName);
            return USER_NAME_CHANGED;
        }
        catch (UserAlreadyExistsException e) {
            System.err.println(e.getMessage());
            return ERR_USER_ALREADY_EXISTS;
        }
        catch(UserDoesNotExistException e){
            System.err.println(e.getMessage());
            return ERR_USER_DOES_NOT_EXIST;
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET @Path("changeUserPassword")
    public String changeUserPassword(@QueryParam("name") String userName, @QueryParam("password") String password){
        try{
            ServiceObjectBuilder.getUserServiceObject().changeUserPassword(userName, password);
            return USER_PASSWORD_CHANGED;
        }
        catch(UserDoesNotExistException e){
            System.err.println(e.getMessage());
            return ERR_USER_DOES_NOT_EXIST;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET @Path("validCredentials")
    public String validCredentials(@QueryParam("name") String userName, @QueryParam("password") String password){
        try {
            return new Gson().toJson(ServiceObjectBuilder.getUserServiceObject().validCredentials(userName, password));
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET @Path("getUserId")
    public String getUserId(@QueryParam("name") String userName){
        try {
            return new Gson().toJson(ServiceObjectBuilder.getUserServiceObject().getUserId(userName));
        }
        catch(UserDoesNotExistException e){
            System.err.println(e.getMessage());
            return ERR_USER_DOES_NOT_EXIST;
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET @Path("addUser")
    public String addUser(@QueryParam("name") String userName, @QueryParam("password") String password){
        try{
            ServiceObjectBuilder.getUserServiceObject().addUser(userName, password);
            return USER_ADDED;
        }
        catch(UserAlreadyExistsException e){
            System.err.println(e.getMessage());
            return ERR_USER_ALREADY_EXISTS;
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET @Path("removeUser")
    public String removeUser(@QueryParam("name") String userName){
        try{
            ServiceObjectBuilder.getUserServiceObject().removeUser(userName);
            return USER_REMOVED;
        }
        catch(UserDoesNotExistException e){
            System.err.println(e.getMessage());
            return ERR_USER_DOES_NOT_EXIST;
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET @Path("getStatusForUser")
    public String getStatusForUser(@QueryParam("name") String userName){
        try{
            return new Gson().toJson(ServiceObjectBuilder.getUserServiceObject().getStatusForUser(userName));
        }
        catch (UserDoesNotExistException e){
            System.err.println(e.getMessage());
            return ERR_USER_DOES_NOT_EXIST;
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET @Path("setStatusForUser")
    public String setStatusForUser(@QueryParam("name") String userName, @QueryParam("status") Boolean status){
        try{
            ServiceObjectBuilder.getUserServiceObject().setStatusForUser(userName, status);
            return STATUS_CHANGED;
        }
        catch(UserDoesNotExistException e){
            System.err.println(e.getMessage());
            return ERR_USER_DOES_NOT_EXIST;
        }
        catch(Exception e){
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @GET @Path("getGroupsForUser")
    public String getGroupsForUser(@QueryParam("name") String userName){
        try{
            return new Gson().toJson(ServiceObjectBuilder.getUserServiceObject().getGroupsForUser(userName));
        }
        catch(UserDoesNotExistException e){
            System.err.println(ERR_USER_DOES_NOT_EXIST);
            return null;
        }
        catch (Exception e){
            System.err.println(e.getMessage());
            return null;
        }
    }

}