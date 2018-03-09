package im.model.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Chats {
    private static Chats chats = null;

    ObservableList<Chat> chatList = FXCollections.observableArrayList();

    private Chats(){
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
            if(c.getName().equals(name)) return c;
        }
        return null;
    }

    public Chat getChatById(String id) {
        for (Chat c : chatList) {
            if(c.getStringId().equals(id)) return c;
        }
        return null;
    }

    public void addChat(Chat chat){
        chatList.add(new Chat("s"));
    }

    public boolean contains(int id) {
        for (Chat c : chatList){
            if(c.getId() == id) return true;
        }
        return false;
    }

    public static Chats getInstance() {
        if (chats == null) {
            chats = new Chats();
        }
        return chats;
    }
}
