package service.classes;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class ClientList {
    private static ClientList instance;
    private List<InetAddress> addresses;

    private ClientList(){
        addresses = new ArrayList<>();
    }

    public void initUpdate(InetAddress address){
        addresses.add(address);
    }

    public void stopUpdate(InetAddress address) {
        addresses.remove(address);
    }


    public ClientList getInstance(){
        if (instance == null) instance = new ClientList();
        return instance;
    }
}
