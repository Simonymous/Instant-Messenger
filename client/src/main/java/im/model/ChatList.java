package im.model;

import im.InstantMessengerClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import im.model.interfaces.Group;

import java.util.ArrayList;
import java.util.List;

/**
 * represents all chats in client
 */
public class ChatList {
    private static ChatList chatListInstance = null;

    private InstantMessengerClient mainApp;
    ObservableList<Chat> chatList = FXCollections.observableArrayList();

    /**
     * standard constructor
     */
    private ChatList() {
    }

    /**
     * returns a Chat object with given name
     *
     * @param name the Chatname for return
     * @return the Chat object
     */
    public Chat getChatByName(String name) {
        for (Chat c : chatList) {
            if (c.getName().equals(name)) return c;
        }
        return null;
    }

    /**
     * return a Chat object with given ID
     *
     * @param id the ID for return
     * @return the Chat object
     */
    public Chat getChatById(String id) {
        for (Chat c : chatList) {
            if (c.getStringId().equals(id)) return c;
        }
        return null;
    }

    /**
     * return a Chat object with given ID
     *
     * @param id the ID for return
     * @return the Chat object
     */
    public Chat getChatById(int id) {
        for (Chat c : chatList) {
            if (c.getStringId().equals(Integer.toString(id))) return c;
        }
        return null;
    }

    /**
     * remove a Chat object with given ID
     *
     * @param id the ID for remove
     */
    public void removeChat(int id) {
        Chat chat = null;
        for (Chat c : chatList) {
            if (c.getId() == id) {
                chat = c;
            }
        }
        chatList.remove(chat);
        mainApp.getChatOverviewController().setListView(chatList);
        mainApp.getChatViewController().setChat(null);
    }

    /**
     * updates a chat, if it is in List else the chat is added
     *
     * @param group the group with the new information
     */
    public void updateChat(Group group) {
        if (!contains(group.getGroupId())) {
            chatList.add(new Chat(group));
            return;
        }
        Chat c = getChatById(group.getGroupId());
        c.setName(group.getGroupName());
        mainApp.getChatOverviewController().setListView(chatList);
    }

    /**
     * adds the given chat to list
     *
     * @param chat the chat to add
     */
    public void addChat(Chat chat) {
        chatList.add(chat);
    }

    /**
     * check if the chat with given id is already in list
     *
     * @param id the id for check
     * @return
     */
    public boolean contains(int id) {
        for (Chat c : chatList) {
            if (c.getId() == id) return true;
        }
        return false;
    }

    /**
     * gets the instance of the Singelton Object
     *
     * @return the instance
     */
    public static ChatList getInstance() {
        if (chatListInstance == null) {
            chatListInstance = new ChatList();
        }
        return chatListInstance;
    }

    public ObservableList<Chat> getChatList() {
        return chatList;
    }

    public void setMainApp(InstantMessengerClient mainApp) {
        this.mainApp = mainApp;
    }
}
