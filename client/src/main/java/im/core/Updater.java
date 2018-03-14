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
            c.addMessage(m);
        }
    }

    public void updateLocalGroups() {
        List<Integer> groupsOfUser = urci.getGroupsOfUser(OwnUser.getInstance().getUserStringId());
        for (Integer id : groupsOfUser) {
            ChatList.getInstance().updateChat(grci.getGroupById(id.toString()));
        }
    }

    public void updateGroup(Group grp) {
        grci.changeGroup(Integer.toString(grp.getGroupId()), grp);
        updateLocalGroups();
    }

    public void deleteGroup(String id) {
        grci.removeGroup(id);
        deleteLocalGroup(id);
    }

    public void deleteLocalGroup(String id) {
        ChatList.getInstance().removeChat(Integer.parseInt(id));
    }

    public void addGroup(String name, ArrayList<Integer> userIds) {
        Group group = grci.addGroup(new GroupImpl(name));
        String groupId = Integer.toString(group.getGroupId());
        System.out.println(groupId);
        for (Integer i : userIds) {
            grci.addUserToGroup(groupId, i.toString());
        }
        updateLocalGroups();
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
        updateLocalGroups();
        for (Chat c : ChatList.getInstance().getChatList()) {
            updateMessagesForGroup(c.getStringId());
        }
    }
}
