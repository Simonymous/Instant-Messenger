package im.model.classes;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class Chats {
    ObservableList<Chat> chatList = FXCollections.observableArrayList();

    public Chats(){
        List<Chat> chats = new ArrayList<Chat>();
        chats.add(new Chat(1));
        chats.add(new Chat());
        chatList.setAll(chats);
    }

    public ObservableList getChatList() {
        return chatList;
    }

    public Chat getChatByName(String name) {
        for (Chat c : chatList) {
            if(c.getName().equals(name)) return c;
        }
        return null;
    }

    public void addChat(Chat chat){
        chatList.add(new Chat("s"));
    }
}
