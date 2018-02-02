package service.classes;

import builder.DaoObjectBuilder;
import builder.ModelObjectBuilder;
import dao.interfaces.MessageDao;
import model.interfaces.Group;
import model.interfaces.Message;
import model.interfaces.User;
import service.interfaces.MessageService;

import java.util.ArrayList;

import static service.constants.ServiceConstants.ERR_MSG_MESSAGE_DOES_NOT_EXIST;

//TODO Throws Klausel zu Methoden hinzufügen die Exceptions werfen können
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

    public void removeMessage(long id) {
        //TODO MessageDoesNotExistException
        Message message = ModelObjectBuilder.getMessageObject();
        message.setMessageId(id);

        messageDao.deleteMessage(message);
    }

    public Message getMessageById(long id) {
        //TODO MessageDoesNotExistException
      Message message = ModelObjectBuilder.getMessageObject();
      message.setMessageId(id);
      return messageDao.getMessageById(message);

    }

    public String getMessageContentById(long id) {
        //TODO MessageDoesNotExistException
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
}
