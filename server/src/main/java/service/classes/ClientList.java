package service.classes;

import service.interfaces.ServerNotify;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

//TODO: Documentation
public class ClientList {
    private static ClientList instance;
    private List<InetAddress> addresses;

    private ClientList() {
        addresses = new ArrayList<>();
    }

    public void initUpdate(InetAddress address) {
        addresses.add(address);
    }

    public void stopUpdate(InetAddress address) {
        addresses.remove(address);
    }

    public void notifyUpdateGroup() {
        for (InetAddress add : addresses) {
            new ServerNotifyImpl(add.getHostAddress()).notifyUpdatedGroup();
        }
    }

    public void notifyRemoveGroup(String id) {
        for (InetAddress add : addresses) {
            new ServerNotifyImpl(add.getHostAddress()).notifyRemovedGroup(id);
        }
    }

    public void notifyNewMessage(String id) {
        for (InetAddress add : addresses) {
            new ServerNotifyImpl(add.getHostAddress()).notifyNewMessage(id);
        }
    }

    public static ClientList getInstance() {
        if (instance == null) instance = new ClientList();
        return instance;
    }
}
