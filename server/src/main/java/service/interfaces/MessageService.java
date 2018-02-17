package service.interfaces;

import model.interfaces.Message;
import rest.exceptions.*;

import java.util.ArrayList;

public interface MessageService {
    void addMessage(int groupId, int userId, String content);
    void removeMessage(long id) throws MessageDoesNotExistException ;
    Message getMessageById(long id) throws MessageDoesNotExistException;
    String getMessageContentById(long id) throws MessageDoesNotExistException;
    ArrayList<Message> getMessagesForUser(int userId) throws UserDoesNotExistException;
    ArrayList<Message> getMessagesForGroup(int groupId) throws GroupDoesNotExistException;

}
