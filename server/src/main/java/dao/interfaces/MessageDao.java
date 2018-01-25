package dao.interfaces;

import model.interfaces.Message;

import java.util.ArrayList;

public interface MessageDao {
    public void save(Message message);
    public void delete(Message message);
    public Message getMessage(int messageId);
    public ArrayList<Message> getGroupMessages(int groupId);
    public ArrayList<Message> getUserMessages(int userId);
}
