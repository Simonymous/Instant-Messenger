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

/**
 * Class for updating Changes on the Server or on Client
 */
public class Updater {
    private UserRestClientImpl urci;
    private GroupRestClientImpl grci;

    public Updater() {
        urci = new UserRestClientImpl();
        grci = new GroupRestClientImpl();
    }

    /**
     * updates the local messages for the given group
     *
     * @param id the groupID for message updates
     */
    public void updateMessagesForGroup(String id) {
        List<Message> messages = grci.getMessagesOfGroup(id, ""); //TODO: I don't know for what pages is.
        Chat c = ChatList.getInstance().getChatById(id);
        for (Message m : messages) {
            c.addMessage(m);
        }
        System.out.println("Local Chat Messages für Gruppe "+id+"updatet");

    }

    /**
     * updates all local groups
     */
    public void updateLocalGroups() {
        List<Integer> groupsOfUser = urci.getGroupsOfUser(OwnUser.getInstance().getUserStringId());
        for (Integer id : groupsOfUser) {
            ChatList.getInstance().updateChat(grci.getGroupById(id.toString()));
        }
        System.out.println("Local Groups updatet");
    }

    /**
     * updates a group on server and all local groups
     *
     * @param grp the group for update
     */
    public void updateGroup(Group grp) {
        grci.changeGroup(Integer.toString(grp.getGroupId()), grp);
        updateLocalGroups();
    }

    /**
     * delete a group on server an local
     *
     * @param id the groupID for delete
     */
    public void deleteGroup(String id) {
        grci.removeGroup(id);
        deleteLocalGroup(id);
    }

    /**
     * delete the local group
     *
     * @param id the groupID for delete
     */
    public void deleteLocalGroup(String id) {
        ChatList.getInstance().removeChat(Integer.parseInt(id));
    }

    /**
     * adds a group at the server
     *
     * @param name    the group name
     * @param userIds the userIDs which are in group
     */
    public void addGroup(String name, ArrayList<Integer> userIds) {
        Group group = grci.addGroup(new GroupImpl(name));
        String groupId = Integer.toString(group.getGroupId());
        System.out.println(groupId);
        for (Integer i : userIds) {
            grci.addUserToGroup(groupId, i.toString());
        }
        updateLocalGroups();
    }

    /**
     * updates all local users
     */
    public void updateLocalUsers() {
        for (String id : urci.getAllUser().getIds()) {
            if(!UserList.getInstance().containsUser(Integer.parseInt(id))) {
                UserList.getInstance().addUser(urci.getUserById(id));
            }
        }
        System.out.println("Local Users updatet");
    }

    /**
     * updates the whole local data
     */
    public void updateAll() {
        updateLocalUsers();
        updateLocalGroups();
        for (Chat c : ChatList.getInstance().getChatList()) {
            updateMessagesForGroup(c.getStringId());
        }
    }
}
