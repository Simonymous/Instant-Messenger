package im.model.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private String name;
    ObservableList MessageList = FXCollections.observableArrayList();

    public Chat() {
        List<ClientMessage> list = new ArrayList<ClientMessage>();
        list.add(new ClientMessage("manuel", "hallo"));
        list.add(new ClientMessage("simon", "hi"));
        MessageList.setAll(list);
        name = "chat A";
    }

    public Chat(int i) {
        List<ClientMessage> list = new ArrayList<ClientMessage>();
        list.add(new ClientMessage("alex", "ich bin schwul"));
        list.add(new ClientMessage("manuel", "ich weis"));
        MessageList.setAll(list);
        name = "chat B";
    }

    public Chat(String i) {
        List<ClientMessage> list = new ArrayList<ClientMessage>();
        list.add(new ClientMessage("niklauch", "ich bin dünn"));
        list.add(new ClientMessage("simon", "echt??"));
        MessageList.setAll(list);
        name = "Chat C";
    }

    public ObservableList getMessageList() {
        return MessageList;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
