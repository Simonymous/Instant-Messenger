package im.model.classes;

import im.core.ClientNotify;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.interfaces.Group;
import model.interfaces.User;
import model.interfaces.Message;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private int id;
    private String name;
    ObservableList<ClientMessage> messageList = FXCollections.observableArrayList();

    public Chat() {
        List<ClientMessage> list = new ArrayList<ClientMessage>();
        list.add(new ClientMessage("manuel", "hallo"));
        list.add(new ClientMessage("simon", "hi"));
        messageList.setAll(list);
        name = "chat A";
    }

    public Chat(int i) {
        List<ClientMessage> list = new ArrayList<ClientMessage>();
        list.add(new ClientMessage("alex", "ich bin schwul"));
        list.add(new ClientMessage("manuel", "ich weis"));
        messageList.setAll(list);
        name = "chat B";
    }

    public Chat(String i) {
        List<ClientMessage> list = new ArrayList<ClientMessage>();
        list.add(new ClientMessage("niklauch", "ich bin dünn"));
        list.add(new ClientMessage("simon", "echt??"));
        messageList.setAll(list);
        name = "Chat C";
    }

    public Chat(Group group) {
        id = group.getGroupId();
        name = group.getGroupName();
    }

    public boolean contains(long id) {
        for (ClientMessage cm :messageList) {
            if (cm.getId() == id) return true;
        }
        return false;
    }

    public void addMessage(Message m) {
        //TODO
    }

    public ObservableList<ClientMessage> getMessageList() {
        return messageList;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getStringId() {
        return String.format("%d", id);
    }

    @Override
    public String toString() {
        return name;
    }
}
