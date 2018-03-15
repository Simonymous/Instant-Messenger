package rest.classes;

import java.net.InetAddress;

/**
 *  class to hold port and address of a client (json)
 */
public class JSONClientAddress {
    public int port;
    public InetAddress address;

    /**
     * constructor with given port and address
     * @param p (port)
     * @param a (address)
     */
    public JSONClientAddress(int p, InetAddress a) {
        port = p;
        address = a;
    }
}
