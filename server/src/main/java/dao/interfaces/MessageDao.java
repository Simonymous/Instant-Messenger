package dao.interfaces;

import model.interfaces.Group;
import model.interfaces.Message;
import model.interfaces.User;

import java.util.ArrayList;

public interface MessageDao {
    void createMessage(Message aMessage);
    void deleteMessage(Message aMessage);

    Message getMessageById(Message aMessage);
    ArrayList<Message> getMessagesByUser(User aUser);
    ArrayList<Message> getMessegesByGroup(Group aGroup);
}
