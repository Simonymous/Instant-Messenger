package service.classes;

import builder.DaoObjectBuilder;
import builder.ModelObjectBuilder;
import dao.interfaces.GroupDao;
import dao.interfaces.MessageDao;
import dao.interfaces.UserDao;
import model.interfaces.Group;
import model.interfaces.Message;
import model.interfaces.User;
import rest.exceptions.GroupDoesNotExistException;
import rest.exceptions.MessageDoesNotExistException;
import rest.exceptions.UserDoesNotExistException;
import service.interfaces.MessageService;

import java.util.ArrayList;

import static service.constants.ServiceConstants.ERR_MSG_MESSAGE_DOES_NOT_EXIST;

public class MessageServiceImpl implements MessageService{
    private MessageDao messageDao;
    private UserDao userDao;
    private GroupDao groupDao;

    public MessageServiceImpl() {
        messageDao = DaoObjectBuilder.getMessageDaoObject();
        userDao = DaoObjectBuilder.getUserDaoObject();
        groupDao = DaoObjectBuilder.getGroupDaoObject();
    }

    public void addMessage(int groupId, int userId, String content) {
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

    public ArrayList<Message> getMessagesForUser(int userId) throws UserDoesNotExistException{
        if(!doesUserExist(userId)){
            throw new UserDoesNotExistException();
        }
        User user = ModelObjectBuilder.getUserObject();
        user.setUserId(userId);
        return messageDao.getMessagesByUser(user);
    }

    public ArrayList<Message> getMessagesForGroup(int groupId) throws GroupDoesNotExistException {
        if(!doesGroupExist(groupId)){
            throw new GroupDoesNotExistException();
        }
        Group group = ModelObjectBuilder.getGroupObject();
        group.setGroupId(groupId);
        return messageDao.getMessagesByGroup(group);
    }

    //TODO Methode auslagern
    public boolean doesGroupExist(int id) {
        for (Group group : groupDao.getAllGroups()) {
            if(group == null){
                return false;
            }
            if(group.getGroupId() == id) {
                return true;
            }
        }
        return false;
    }

    //TODO Methode aulagern
    public boolean doesUserExist(int id) {
        for (User user : userDao.getUsersFromDB()) {
            if(user == null){
                return false;
            }
            if(user.getUserId() == id) {
                return true;
            }
        }
        return false;
    }

    public boolean doesMessageExist(long id) {
        for (Message message : messageDao.getMessagesFromDB()) {
            if(message == null){
                return false;
            }
            if(message.getMessageId() == id) {
                return true;
            }
        }
        return false;
    }
}
