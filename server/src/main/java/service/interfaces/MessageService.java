package service.interfaces;

import model.interfaces.Message;
import rest.exceptions.MessageDoesNotExistException;

import java.util.ArrayList;

public interface MessageService {
    void addMesssage(int groupId, int userId, String content);
    void removeMessage(long id) throws MessageDoesNotExistException;
    Message getMessageById(long id) throws MessageDoesNotExistException;
    String getMessageContentById(long id) throws MessageDoesNotExistException;
    ArrayList<Message> getMessagesForUser(int userId);
    ArrayList<Message> getMessagesForGroup(int groupId);

}
