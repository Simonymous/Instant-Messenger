package im.core;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Notify server
 */
public class PushServer {

    /**
     * standard constructor, start an http server for notifying the client for changes
     *
     * @throws IOException
     */
    public PushServer() throws IOException {
        String baseUrl = "http://localhost:4435";

        final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
                URI.create(baseUrl), new ResourceConfig(ClientNotify.class), false);
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                server.shutdownNow();
            }
        }));
        server.start();
    }
}
