package im.model;

import im.InstantMessengerClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.interfaces.Group;

import java.util.ArrayList;
import java.util.List;

public class ChatList {
    private static ChatList chatListInstance = null;

    private InstantMessengerClient mainApp;

    ObservableList<Chat> chatList = FXCollections.observableArrayList();

    private ChatList() {
        List<Chat> chats = new ArrayList<>();
        chatList.setAll(chats);

    }

    public ObservableList<Chat> getChatList() {
        return chatList;
    }

    public Chat getChatByName(String name) {
        for (Chat c : chatList) {
            if (c.getName().equals(name)) return c;
        }
        return null;
    }

    public Chat getChatById(String id) {
        for (Chat c : chatList) {
            if (c.getStringId().equals(id)) return c;
        }
        return null;
    }

    public Chat getChatById(int id) {
        for (Chat c : chatList) {
            if (c.getStringId().equals(Integer.toString(id))) return c;
        }
        return null;
    }

    public void removeChat(int id) {
        Chat chat = null;
        for (Chat c : chatList) {
            if (c.getId() == id) {
                chat = c;
            }
        }
        chatList.remove(chat);
        mainApp.getChatOverviewController().setListView(chatList);
    }

    public void updateChat(Group group) {
        if (!contains(group.getGroupId())) {
            chatList.add(new Chat(group));
            return;
        }
        Chat c = getChatById(group.getGroupId());
        c.setName(group.getGroupName());
    }

    public void addChat(Chat chat) {
        chatList.add(chat);
    }

    public boolean contains(int id) {
        for (Chat c : chatList) {
            if (c.getId() == id) return true;
        }
        return false;
    }

    public static ChatList getInstance() {
        if (chatListInstance == null) {
            chatListInstance = new ChatList();
        }
        return chatListInstance;
    }

    public void setMainApp(InstantMessengerClient mainApp) {
        this.mainApp = mainApp;
    }
}
