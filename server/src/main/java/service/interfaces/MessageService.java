package service.interfaces;

import model.interfaces.Message;

import java.util.ArrayList;

public interface MessageService {
    void addMesssage(int groupId, int userId, String content);
    void removeMessage(int id);
    Message getMessageById(int id);
    String getMessageContentById(int id);
    ArrayList<Message> getMessagesForUser(int userId);
    ArrayList<Message> getMessagesForGroup(int groupId);

}
