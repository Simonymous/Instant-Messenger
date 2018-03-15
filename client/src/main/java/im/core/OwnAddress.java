package im.core;

import java.net.InetAddress;

/**
 * This Class represents the address of the client
 */
public class OwnAddress {
    private static OwnAddress ownAddress = null;

    private InetAddress address;
    private int port;
    private boolean isCreated = false;

    private OwnAddress() {
    }

    /**
     * creates the local address of client
     *
     * @param a
     * @param p
     */
    public void createAddress(InetAddress a, int p) {
        if (!isCreated) {
            address = a;
            port = p;
            isCreated = true;
        }
    }

    public int getPort() {
        return port;
    }

    public InetAddress getAddress() {
        return address;
    }

    public boolean isCreated() {
        return isCreated;
    }

    /**
     * gets the instance of the Singelton Object
     *
     * @return the instance
     */
    public static OwnAddress getInstance() {
        if (ownAddress == null) {
            ownAddress = new OwnAddress();
        }
        return ownAddress;
    }
}
