package im.core;

import java.net.InetAddress;

/**
 * This Class represents the logged in User
 */
public class OwnAddress {
    private static OwnAddress ownAddress = null;

    private InetAddress address;
    private int port;

    private boolean isCreated;


    private OwnAddress() {

    }

    public void createAddress(InetAddress a, int p) {
        if (!isCreated) {
            address = a;
            port = p;
        }
    }

    public int getPort () {
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
