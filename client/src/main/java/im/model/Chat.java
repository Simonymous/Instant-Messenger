package im.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.interfaces.Group;
import model.interfaces.Message;

public class Chat {
    private int id;
    private String name;
    ObservableList<ClientMessage> messageList = FXCollections.observableArrayList();

    public Chat(Group group) {
        id = group.getGroupId();
        name = group.getGroupName();
    }

    public boolean contains(long id) {
        for (ClientMessage cm : messageList) {
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
