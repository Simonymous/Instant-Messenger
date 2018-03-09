package im.core;

import im.model.classes.Chat;
import im.model.classes.Chats;
import model.interfaces.Message;
import rest.services.GroupRestClientImpl;
import rest.services.UserRestClientImpl;

import java.util.List;

public class Updater {
    private UserRestClientImpl urci;
    private GroupRestClientImpl grci;

    public Updater(){
        urci = new UserRestClientImpl();
        grci = new GroupRestClientImpl();
    }

    public void updateMessagesForGroup(String id) {
        List<Message> messages = grci.getMessagesOfGroup(id,""); //TODO: I don't know for what pages is.
        Chat c = Chats.getInstance().getChatById(id);
        for (Message m : messages) {
            if (!c.contains(m.getMessageId())){
                c.addMessage(m);
            }
        }
    }

    public void updateGroups() {
        List<Integer> groupsOfUser = urci.getGroupsOfUser(User.getInstance().getUserStringId());
        for (Integer id : groupsOfUser) {
            if(!Chats.getInstance().contains(id)){
                Chats.getInstance().addChat(new Chat(grci.getGroupById(id.toString())));
                updateMessagesForGroup(id.toString());
            }
        }
    }

    public void updateAll() {
        updateGroups();
        for (Chat c : Chats.getInstance().getChatList()){
            updateMessagesForGroup(c.getStringId());
        }
    }
}
