package service.classes;

import builder.DaoObjectBuilder;
import builder.ModelObjectBuilder;
import dao.interfaces.MessageDao;
import model.interfaces.Group;
import model.interfaces.Message;
import model.interfaces.User;
import rest.exceptions.MessageDoesNotExistException;
import service.interfaces.MessageService;

import java.util.ArrayList;

import static service.constants.ServiceConstants.ERR_MSG_MESSAGE_DOES_NOT_EXIST;

public class MessageServiceImpl implements MessageService{
    private MessageDao messageDao;

    public MessageServiceImpl() {
        messageDao = DaoObjectBuilder.getMessageDaoObject();
    }

    public void addMesssage(int groupId, int userId, String content) {
      Group group = ModelObjectBuilder.getGroupObject();
      group.setGroupId(groupId);
      User user = ModelObjectBuilder.getUserObject();
      user.setUserId(userId);

      Message message = ModelObjectBuilder.getMessageObject(group, user, content);
      messageDao.createMessage(message);
    }

    public void removeMessage(long id) throws MessageDoesNotExistException{
        if(!doesMessageExist(id)){
            throw new MessageDoesNotExistException();
        }
        Message message = ModelObjectBuilder.getMessageObject();
        message.setMessageId(id);

        messageDao.deleteMessage(message);
    }

    public Message getMessageById(long id) throws MessageDoesNotExistException{
        if(!doesMessageExist(id)){
            throw new MessageDoesNotExistException();
        }
        Message message = ModelObjectBuilder.getMessageObject();
        message.setMessageId(id);
        return messageDao.getMessageById(message);

    }

    public String getMessageContentById(long id) throws MessageDoesNotExistException{
        if(!doesMessageExist(id)){
            throw new MessageDoesNotExistException();
        }
        Message message = ModelObjectBuilder.getMessageObject();
        message.setMessageId(id);
        return messageDao.getMessageById(message).getContent();
    }

    public ArrayList<Message> getMessagesForUser(int userId) {
        User user = ModelObjectBuilder.getUserObject();
        user.setUserId(userId);
        return messageDao.getMessagesByUser(user);
    }

    public ArrayList<Message> getMessagesForGroup(int groupId) {
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(groupId);
        return messageDao.getMessagesByGroup(group);
    }



    public boolean doesMessageExist(long id) {
        for (Message message : messageDao.getMessagesFromDB()) {
            if(message.getMessageId() == id) {
                return true;
            }
        }
        return false;
    }
}
