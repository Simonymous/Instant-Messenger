package im.core;

import im.model.Chat;
import im.model.ChatList;
import im.model.UserList;
import model.classes.GroupImpl;
import model.interfaces.Group;
import model.interfaces.Message;
import rest.services.GroupRestClientImpl;
import rest.services.UserRestClientImpl;

import java.util.ArrayList;
import java.util.List;

public class Updater {
    private UserRestClientImpl urci;
    private GroupRestClientImpl grci;

    public Updater() {
        urci = new UserRestClientImpl();
        grci = new GroupRestClientImpl();
    }

    public void updateMessagesForGroup(String id) {
        List<Message> messages = grci.getMessagesOfGroup(id, ""); //TODO: I don't know for what pages is.
        Chat c = ChatList.getInstance().getChatById(id);
        for (Message m : messages) {
            if (!c.contains(m.getMessageId())) {
                c.addMessage(m);
            }
        }
    }

    public void updateGroups() {
        List<Integer> groupsOfUser = urci.getGroupsOfUser(OwnUser.getInstance().getUserStringId());
        if (!groupsOfUser.isEmpty()) {
            for (Integer id : groupsOfUser) {
                if (!ChatList.getInstance().contains(id)) {
                    ChatList.getInstance().addChat(new Chat(grci.getGroupById(id.toString())));
                    updateMessagesForGroup(id.toString());
                }
            }
        }
    }

    public void deleteGroup(String id) {
        grci.removeGroup(id);
        ChatList.getInstance().removeChat(Integer.parseInt(id));
    }

    public void addGroup(String name, ArrayList<Integer> userIds) {
        Group group = grci.addGroup(new GroupImpl(name));
//        String groupId = Integer.toString(group.getGroupId());
//        System.out.println(groupId);
//        if (!userIds.isEmpty()) {
//            for (Integer i : userIds) {
//                grci.addUserToGroup(groupId, i.toString());       TODO: Add Users
//            }
//        }
        //updateGroups();
    }

    public void updateLocalUsers() {
        for (String id : urci.getAllUser().getIds()) {
            if (!UserList.getInstance().containsUser(Integer.parseInt(id))) {
                UserList.getInstance().addUser(urci.getUserById(id));
            }
        }
    }

    public void updateAll() {
        updateLocalUsers();
        updateGroups();
        for (Chat c : ChatList.getInstance().getChatList()) {
            updateMessagesForGroup(c.getStringId());
        }
    }
}
