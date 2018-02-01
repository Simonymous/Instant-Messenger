package main;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path( HelloWorldService.webContextPath )
public class HelloWorldService
{
    static final String webContextPath = "/helloworld";

    @GET @Path("hallo") @Produces( MediaType.TEXT_PLAIN )
    public String halloPlainText( @QueryParam("name") String name )
    {
        return "Plain-Text: Hallo " + name;
    }

    @GET @Produces( MediaType.TEXT_HTML )
    public String halloHtml( @QueryParam("name") String name )
    {
        return "<html><title>HelloWorld</title><body><h2>Html: Hallo " + name + "</h2></body></html>";
    }
}