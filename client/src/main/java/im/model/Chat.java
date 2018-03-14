package im.model;

import im.core.OwnUser;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.interfaces.Group;
import model.interfaces.Message;
import model.interfaces.User;

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
        if (contains(m.getMessageId())) return;
        User u = UserList.getInstance().getUser(m.getUser());
        String username;
        if (m.getUser() == OwnUser.getInstance().getUserId()) {
            username = OwnUser.getInstance().getUsername();
        } else if (u == null) {
            username = Integer.toString(m.getUser());
        } else {
            username = u.getUsername();
        }
        messageList.add(new ClientMessage(m.getMessageId(), username, m.getContent()));
    }

    public ObservableList<ClientMessage> getMessageList() {
        return messageList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
