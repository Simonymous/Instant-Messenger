package service.classes;

import service.interfaces.ServerNotify;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.util.ArrayList;
import java.util.List;

//TODO: Documentation
public class ClientList {
    private static final String URL_PRAEFIX = "http:/";
    private static ClientList instance;
    private List<InetSocketAddress> addresses;

    private ClientList() {
        addresses = new ArrayList<>();
    }

    public void initUpdate(InetSocketAddress address) {
        addresses.add(address);
        System.out.println("Notification enabled for: "+address);
    }

    public void stopUpdate(InetSocketAddress address) {
        addresses.remove(address);
        System.out.println("Notification stopped for: "+address);
    }

    public void notifyUpdateGroup() {
        for (InetSocketAddress add : addresses) {
            new ServerNotifyImpl(URL_PRAEFIX + add.getAddress() + ":"+add.getPort()).notifyUpdatedGroup();
            System.out.println("Client: "+add+" notified->updateGroup");
        }
    }

    public void notifyRemoveGroup(String id) {
        for (InetSocketAddress add : addresses) {
            new ServerNotifyImpl(URL_PRAEFIX + add.getAddress() + ":"+add.getPort()).notifyRemovedGroup(id);
            System.out.println("Client: "+add+" notified->removeGroup");
        }
    }

    public void notifyNewMessage(String id) {
        for (InetSocketAddress add : addresses) {
           new ServerNotifyImpl(URL_PRAEFIX + add.getAddress() + ":"+add.getPort()).notifyNewMessage(id);
            System.out.println("Client: "+URL_PRAEFIX + add.getAddress() + ":"+add.getPort()+" notified->newMessage");
        }
    }

    public static ClientList getInstance() {
        if (instance == null) instance = new ClientList();
        return instance;
    }
}
