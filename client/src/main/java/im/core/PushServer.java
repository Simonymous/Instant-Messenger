package im.core;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
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
        ServerSocket s = new ServerSocket(0);
        int port = s.getLocalPort();
        s.close();
        InetAddress address = InetAddress.getLocalHost();
        OwnAddress.getInstance().createAddress(address, port);
        try {

            String baseUrl = "http://" + address.getHostAddress() + ":" + port;
            final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(
                    URI.create(baseUrl), new ResourceConfig(ClientNotify.class), false);
            Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                @Override
                public void run() {
                    server.shutdownNow();
                }
            }));
            server.start();

            System.out.println(String.format("\nGrizzly-HTTP-Server gestartet mit der URL: %s\n"
                            + "Stoppen des Grizzly-HTTP-Servers mit:      Strg+C\n",
                    baseUrl + ClientNotify.webContextPath));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
