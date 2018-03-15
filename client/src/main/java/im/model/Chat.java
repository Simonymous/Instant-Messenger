package im.model;

import im.core.OwnUser;
import im.model.interfaces.Group;
import im.model.interfaces.Message;
import im.model.interfaces.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * represents a Chat that is shown in the ChatList
 */
public class Chat {
    private int id;
    private String name;
    private final ObservableList<ClientMessage> messageList = FXCollections.observableArrayList();

    /**
     * constructor for initializing a chat with Group object
     *
     * @param group
     */
    public Chat(Group group) {
        id = group.getGroupId();
        name = group.getGroupName();
    }

    /**
     * Check if the message with given id  is already in messageList
     *
     * @param id the id that is check
     * @return
     */
    public boolean contains(long id) {
        for (ClientMessage cm : messageList) {
            if (cm.getId() == id) return true;
        }
        return false;
    }

    /**
     * is called if a new message is send from server to add it to list
     *
     * @param m the message to add
     */
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
