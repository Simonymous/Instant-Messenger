package rest.classes;

import java.net.InetAddress;

/*
 *   Class for JSONClientAddress
 * */
public class JSONClientAddress {
    public int port;
    public InetAddress address;

    public JSONClientAddress(int p, InetAddress a) {
        port = p;
        address = a;
    }
}
