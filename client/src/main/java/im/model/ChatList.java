package im.model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class ChatList {
    private static ChatList chatListInstance = null;

    ObservableList<Chat> chatList = FXCollections.observableArrayList();

    private ChatList() {
        List<Chat> chats = new ArrayList<>();
        chats.add(new Chat(1));
        chats.add(new Chat());
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

    public void removeChat(int id) {
        for (Chat c : chatList) {
            if (c.getId() == id) {
                chatList.remove(c);
            }
        }
    }

    public void addChat(Chat chat) {
        chatList.add(new Chat("s"));
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
}
