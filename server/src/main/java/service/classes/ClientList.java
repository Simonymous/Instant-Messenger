package service.classes;

import service.interfaces.ServerNotify;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

//TODO: Documentation
public class ClientList {
    private static final String URL_PRAEFIX = "http://";
    private static ClientList instance;
    private List<InetAddress> addresses;

    private ClientList() {
        addresses = new ArrayList<>();
    }

    public void initUpdate(InetAddress address) {
        addresses.add(address);
        System.out.println("Notification enabled for: "+address);
    }

    public void stopUpdate(InetAddress address) {
        addresses.remove(address);
        System.out.println("Notification stopped for: "+address);
    }

    public void notifyUpdateGroup() {
        for (InetAddress add : addresses) {
            new ServerNotifyImpl(URL_PRAEFIX + add.getHostAddress() + ":4435").notifyUpdatedGroup();
            System.out.println("Client: "+add+" notified->updateGroup");
        }
    }

    public void notifyRemoveGroup(String id) {
        for (InetAddress add : addresses) {
            new ServerNotifyImpl(URL_PRAEFIX + add.getHostAddress() + ":4435").notifyRemovedGroup(id);
            System.out.println("Client: "+add+" notified->removeGroup");
        }
    }

    public void notifyNewMessage(String id) {
        for (InetAddress add : addresses) {
            new ServerNotifyImpl(URL_PRAEFIX + add.getHostAddress() + ":4435").notifyNewMessage(id);
            System.out.println("Client: "+URL_PRAEFIX+add.getHostAddress()+":4435 notified->newMessage");
        }
    }

    public static ClientList getInstance() {
        if (instance == null) instance = new ClientList();
        return instance;
    }
}
