package dao.interfaces;

import model.interfaces.Message;

import java.util.ArrayList;

public interface MessageDao {
    void createMessage(Message aMessage);
    void deleteMessage(Message aMessage);

    Message getMessageById(Message aMessage);
    ArrayList<Message> getMessagesByUser(Message aMessage);
    ArrayList<Message> getMessegesByGroup(Message aMessage);
}
